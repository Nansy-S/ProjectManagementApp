package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import com.prokopovich.projectmanagement.model.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class AttachmentMySqlDao extends GenericMySqlDao<Attachment> implements AttachmentDao {

    private static final String SQL_SELECT_ALL = "SELECT attachment_id, file, task_id FROM attachments";
    private static final String SQL_SELECT_ONE = "SELECT attachment_id, task_id FROM attachments " +
            "WHERE attachment_id = ?";
    private static final String SQL_SELECT_BY_TASK = "SELECT attachment_id, task_id FROM attachments " +
            "WHERE task_id = ?";
    private static final String SQL_CREATE = "INSERT INTO attachments (file, task_id) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE attachments SET file = ?, task_id = ? WHERE attachment_id = ?";
    private static final String SQL_DELETE = "DELETE FROM attachments WHERE attachment_id = ?";

    private static final Logger LOGGER = LogManager.getLogger(AttachmentMySqlDao.class);

    public AttachmentMySqlDao(){
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
    public String getSqlLastInsert() {
        return SQL_CREATE;
    }

    @Override
    protected Attachment getStatement(ResultSet rs) throws SQLException {
        Attachment attachment = new Attachment();

        attachment.setAttachmentId(rs.getInt(1));
      //  attachment.setFile(rs.getBlob(2));
        attachment.setTaskId(rs.getInt(2));
        return attachment;
    }

    @Override
    protected void setStatement(Attachment attachment, PreparedStatement statement) throws SQLException {
        statement.setBlob(1, attachment.getFile());
        statement.setInt(2, attachment.getTaskId());
    }

    @Override
    public boolean update(Attachment attachment) throws DaoException {
        LOGGER.trace("update attachment method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            setStatement(attachment, statement);
            statement.setInt(3, attachment.getAttachmentId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public boolean delete(int attachmentId) throws DaoException {
        LOGGER.trace("delete attachment method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, attachmentId);
            statement.executeUpdate();
            LOGGER.debug("attachment deleted");
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return true;
    }

    @Override
    public Collection<Attachment> findAllByTaskId(int taskId) throws DaoException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        return findByParameter(SQL_SELECT_BY_TASK, taskId);
    }
}
