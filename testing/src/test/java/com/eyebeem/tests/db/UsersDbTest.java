package com.eyebeem.tests.db;

import com.eyebeem.tests.BaseDbTest;
import com.eyebeem.tests.util.DatabaseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

/**
 * DB tests for users table (aligned with db test/users.json).
 */
public class UsersDbTest extends BaseDbTest {

    @Test
    public void usersTableExists() throws SQLException {
        Assert.assertTrue(DatabaseUtil.tableExists("users"));
    }

    @Test
    public void userCount_atLeast5() throws SQLException {
        Assert.assertTrue(DatabaseUtil.getUserCount() >= 5, "Expect at least 5 users (from db test snapshot)");
    }

    @Test
    public void user1_exists_withEmail() throws SQLException {
        Object[] row = DatabaseUtil.getUserRow(1);
        Assert.assertNotNull(row);
        Assert.assertEquals(row[2], "test@eyebeem.com");
        Assert.assertEquals(row[1], "Test User");
    }

    @Test
    public void user2_exists_withEmail() throws SQLException {
        Object[] row = DatabaseUtil.getUserRow(2);
        Assert.assertNotNull(row);
        Assert.assertEquals(row[2], "jane@eyebeem.com");
    }

    @Test
    public void user3_exists_withEmail() throws SQLException {
        Object[] row = DatabaseUtil.getUserRow(3);
        Assert.assertNotNull(row);
        Assert.assertEquals(row[2], "imaad@imaad.com");
    }

    @Test
    public void user4_exists_withEmail() throws SQLException {
        Object[] row = DatabaseUtil.getUserRow(4);
        Assert.assertNotNull(row);
        Assert.assertEquals(row[2], "aryan@aryan.com");
    }

    @Test
    public void user5_exists_withEmail() throws SQLException {
        Object[] row = DatabaseUtil.getUserRow(5);
        Assert.assertNotNull(row);
        Assert.assertEquals(row[2], "petlover@pet.com");
    }

    @Test
    public void nonExistentUser_returnsNull() throws SQLException {
        Assert.assertNull(DatabaseUtil.getUserRow(999999));
    }
}
