package test;

import BusinessLogic.ResourcesController;
import java.sql.SQLException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class ResourcesControllerTest{

    @Test
    public void CheckValuesTest() throws SQLException, ClassNotFoundException{
        ResourcesController resourcesController = new ResourcesController();
        assertTrue(resourcesController.CheckValues(0, 1, 2, 3, 4, "2024-06-19"));
        assertFalse(resourcesController.CheckValues(5000, 200, 100, 100, 0, "2024-06-19"));
    }
}
