package loader;

import com.epam.web.loader.PropertyLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoaderTest {


    private final static String VALID_FILE = "test.properties";
    private final static String VALID_PROPERTY = "test.login";
    private final static String INVALID_FILE = "invalid.properties";
    private final static String INVALID_PROPERTY = "test.invalid";
    private final static PropertyLoader LOADER = new PropertyLoader();

    @Test
    public void testLoadPropertyWhenValidFileValidProperty() throws IOException {
        //given
        Properties properties = LOADER.load(VALID_FILE);
        String expected = "login";
        //when
        String actual = properties.getProperty(VALID_PROPERTY);
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testLoadPropertyWhenInvalidFileValidPropertyThrowsNullPointerEx() throws IOException {
        //when
        Properties properties = LOADER.load(INVALID_FILE);

    }

    @Test
    public void testLoadPropertyWhenValidFileInvalidPropertyNull() throws IOException {
        //given
        Properties properties = LOADER.load(VALID_FILE);
        //when
        String notExistedProperty = properties.getProperty(INVALID_PROPERTY);
        Assert.assertNull(notExistedProperty);
    }

}
