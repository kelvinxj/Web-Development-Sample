package samples

import org.junit._
import Assert._

@Test
@Ignore
class AppTest {

    @Test
    def testOK() = assertTrue(true)


    @Test
    //this test should not pass
    def testFalse() = assertTrue(false)

//    @Test
//    def testKO() = assertTrue(false)

}


