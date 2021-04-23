package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.Comment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class CommentMySqlDao extends GenericMySqlDao<Comment> implements CommentDao {

    private static final String SQL_SELECT_ALL = "SELECT comment_id, title, text, date_time, author, task_id " +
            "FROM comments";
    private static final String SQL_SELECT_ONE = "SELECT comment_id, title, text, date_time, author, task_id " +
            "FROM comments WHERE comment_id = ?";
    private static final String SQL_SELECT_BY_TASK = "SELECT comment_id, title, text, date_time, author, task_id " +
            "FROM comments WHERE task_id = ?";
    private static final String SQL_SELECT_BY_AUTHOR = "SELECT comment_id, title, text, date_time, author, task_id " +
            "FROM comments WHERE author = ?";
    private static final String SQL_CREATE = "INSERT INTO comments (title, text, date_time, author, task_id) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE comments SET title = ?, text = ?, date_time = ?, author = ?, " +
            "task_id = ? WHERE comment_id = ?";
    private static final String SQL_DELETE = "DELETE FROM comments WHERE comment_id = ?";

    private static final Logger LOGGER = LogManager.getLogger(CommentMySqlDao.class);

    public CommentMySqlDao(){
        super();
    }

    @Override
    public String getSqlSelectAll() {
        return SQL_SELECT_ALL;
    }

    @Override
    public String getSqlSelectOne() {
        return SQL_SELECT_ONE;
    }

    @Override
    public String getSqlCreate() {
        return SQL_CREATE;
    }

    @Override
    protected Comment getStatement(ResultSet rs) throws SQLException {
        Comment comment = new Comment();

        comment.setCommentId(rs.getInt(1));
        comment.setTitle(rs.getString(2));
        comment.setText(rs.getString(3));
        comment.setDatetime(rs.getTimestamp(4));
        comment.setAuthor(rs.getInt(5));
        comment.setTaskId(rs.getInt(6));
        return comment;
    }

    @Override
    public void setStatement(Comment comment, PreparedStatement statement) throws SQLException {
        statement.setString(1, comment.getTitle());
        statement.setString(2, comment.getText());
        statement.setTimestamp(3, comment.getDatetime());
        statement.setInt(4, comment.getAuthor());
        statement.setInt(5, comment.getTaskId());
    }

    @Override
    public boolean updateComment(Comment comment) throws DaoException {
        LOGGER.trace("updateComment method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(comment, statement);
            statement.setInt(6, comment.getCommentId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public boolean deleteComment(int commentId) throws DaoException {
        LOGGER.trace("deleteComment method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
            LOGGER.debug("comment deleted");
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<Comment> findAllByTaskId(int taskId) throws DaoException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        return findByParameter(SQL_SELECT_BY_TASK, taskId);
    }

    @Override
    public Collection<Comment> findAllByAuthor(int authorId) throws DaoException {
        LOGGER.trace("findAllByAuthor method is executed - authorId = " + authorId);
        return findByParameter(SQL_SELECT_BY_AUTHOR, authorId);
    }
}
