/*
 * This file was automatically generated by EvoSuite
 * Wed Jun 04 15:24:26 GMT 2025
 */

package Vintage.mvc;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import Vintage.mvc.Controller;
import Vintage.mvc.Model;
import Vintage.mvc.Viewer;
import java.io.ByteArrayInputStream;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NotYetConnectedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Viewer_ESTest extends Viewer_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Model model0 = new Model();
      Controller controller0 = new Controller(model0);
      Scanner scanner0 = new Scanner("AV5{YyWyy");
      Viewer viewer0 = new Viewer(controller0, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Model model0 = new Model();
      Controller controller0 = new Controller(model0);
      MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("Indique o novo valor base para encomendas grandes pretendido. Atual: ");
      FileChannel fileChannel0 = mockFileOutputStream0.getChannel();
      Scanner scanner0 = new Scanner(fileChannel0);
      Viewer viewer0 = new Viewer(controller0, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NonReadableChannelException");
      
      } catch(NonReadableChannelException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.evosuite.runtime.mock.java.io.EvoFileChannel", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Viewer viewer0 = new Viewer((Controller) null, (Scanner) null);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("Vintage.mvc.Viewer", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      Scanner scanner0 = new Scanner("AV5{YyWyy");
      scanner0.close();
      Viewer viewer0 = new Viewer((Controller) null, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // Scanner closed
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      Model model0 = new Model();
      Controller controller0 = new Controller(model0);
      Scanner scanner0 = new Scanner("AV5{YyWyy");
      scanner0.useDelimiter((Pattern) null);
      Viewer viewer0 = new Viewer(controller0, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Pattern cannot be null
         //
         verifyException("java.util.regex.Matcher", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      byte[] byteArray0 = new byte[4];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) (-84), (byte)2);
      Scanner scanner0 = new Scanner(byteArrayInputStream0);
      Viewer viewer0 = new Viewer((Controller) null, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.io.ByteArrayInputStream", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      Model model0 = new Model();
      Scanner scanner0 = new Scanner("2");
      Controller controller0 = new Controller(model0);
      Viewer viewer0 = new Viewer(controller0, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      Scanner scanner0 = new Scanner("1");
      Model model0 = new Model();
      Controller controller0 = new Controller(model0);
      Viewer viewer0 = new Viewer(controller0, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      Model model0 = new Model();
      Controller controller0 = new Controller(model0);
      Scanner scanner0 = new Scanner("6");
      Viewer viewer0 = new Viewer(controller0, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test9()  throws Throwable  {
      DatagramChannel datagramChannel0 = DatagramChannel.open();
      Scanner scanner0 = new Scanner(datagramChannel0);
      Viewer viewer0 = new Viewer((Controller) null, scanner0);
      // Undeclared exception!
      try { 
        viewer0.run();
        fail("Expecting exception: NotYetConnectedException");
      
      } catch(NotYetConnectedException e) {
      }
  }
}
