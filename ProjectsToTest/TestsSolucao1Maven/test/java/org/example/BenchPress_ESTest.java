/*
 * This file was automatically generated by EvoSuite
 * Wed Jun 04 11:41:42 GMT 2025
 */

package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.time.MockLocalDate;
import org.evosuite.runtime.mock.java.time.MockLocalDateTime;
import org.evosuite.runtime.mock.java.time.MockLocalTime;
import org.example.Atividade;
import org.example.BenchPress;
import org.example.Utilizador;
import org.example.UtilizadorAmador;
import org.example.UtilizadorPraticanteOcasional;
import org.example.UtilizadorProfissional;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class BenchPress_ESTest extends BenchPress_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.now();
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), (-29), (-29));
      UtilizadorAmador utilizadorAmador0 = new UtilizadorAmador();
      utilizadorAmador0.setPeso(1856.08);
      utilizadorAmador0.addAtividade(benchPress0);
      BenchPress benchPress1 = (BenchPress)benchPress0.geraAtividade(utilizadorAmador0, 6.0);
      assertEquals((-23.200000000000003), benchPress1.getPeso(), 0.01);
      assertEquals(1, benchPress1.getRepeticoes());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      LocalDate localDate0 = MockLocalDate.now();
      UtilizadorAmador utilizadorAmador0 = new UtilizadorAmador("oa&j", "", "", 2377, 1, 2377, localDate0, 'o');
      BenchPress benchPress1 = (BenchPress)benchPress0.geraAtividade(utilizadorAmador0, (-1204.66));
      assertEquals(1.0, benchPress1.getPeso(), 0.01);
      assertEquals((-437), benchPress1.getRepeticoes());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.now();
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), 492, 492);
      UtilizadorAmador utilizadorAmador0 = new UtilizadorAmador();
      utilizadorAmador0.addAtividade(benchPress0);
      BenchPress benchPress1 = (BenchPress)benchPress0.geraAtividade(utilizadorAmador0, 6.0);
      assertEquals(0, benchPress1.getRepeticoes());
      assertEquals(393.6, benchPress1.getPeso(), 0.01);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      String string0 = benchPress0.toString();
      assertEquals("Atividade\nId: 1\nData e hora: 14/02/2014 20:21:21\nDura\u00E7ao: 00:00\nFrequencia Cardiaca: 0 bpm\nRepeti\u00E7oes: 0\nPeso: 0.0 kilos\nTipo de atividade: Bench press\n", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      benchPress0.setProximoCodigo(0);
      UtilizadorAmador utilizadorAmador0 = new UtilizadorAmador();
      Atividade atividade0 = benchPress0.geraAtividade(utilizadorAmador0, 563.71);
      assertTrue(atividade0.equals((Object)benchPress0));
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay(0L);
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.of(localDate0, localTime0);
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), 0, 0);
      UtilizadorAmador utilizadorAmador0 = new UtilizadorAmador();
      benchPress0.setProximoCodigo((-195225787));
      BenchPress benchPress1 = (BenchPress)benchPress0.geraAtividade(utilizadorAmador0, 6.0);
      assertEquals(0.0, benchPress1.getPeso(), 0.01);
      assertEquals(0, benchPress1.getRepeticoes());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Month month0 = Month.NOVEMBER;
      LocalDateTime localDateTime0 = MockLocalDateTime.of(1, month0, 1, 1, 1);
      LocalTime localTime0 = MockLocalTime.of(1, 1);
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, 1, 1, 0.8);
      LocalDate localDate0 = MockLocalDate.from(localDateTime0);
      UtilizadorPraticanteOcasional utilizadorPraticanteOcasional0 = new UtilizadorPraticanteOcasional("Atividade\nId: 2\nData e hora: 01/11/0001 01:01:00\nDura\u00E7ao: 01:01\nFrequencia Cardiaca: 1 bpm\nRepeti\u00E7oes: 10\nTipo de atividade: Flex\u00F5es\n", "d@$pJo7~Am4/v,yLA42", "y#r5", 0, 0, 0, localDate0, 'o');
      double double0 = benchPress0.consumoCalorias(utilizadorPraticanteOcasional0);
      assertEquals(Double.POSITIVE_INFINITY, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay(0L);
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.of(localDate0, localTime0);
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), 0, 0);
      UtilizadorAmador utilizadorAmador0 = new UtilizadorAmador();
      utilizadorAmador0.setPeso(1856.08);
      benchPress0.setPeso((-821.499215));
      double double0 = benchPress0.consumoCalorias(utilizadorAmador0);
      assertEquals((-2425.5957735569473), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      // Undeclared exception!
      try { 
        benchPress0.geraAtividade((Utilizador) null, 213);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.BenchPress", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      benchPress0.setTempo((LocalTime) null);
      Object object0 = benchPress0.clone();
      // Undeclared exception!
      try { 
        benchPress0.equals(object0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.Atividade", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      // Undeclared exception!
      try { 
        benchPress0.consumoCalorias((Utilizador) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.BenchPress", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      // Undeclared exception!
      try { 
        benchPress0.consumoCalorias(utilizadorProfissional0);
        fail("Expecting exception: ArithmeticException");
      
      } catch(ArithmeticException e) {
         //
         // / by zero
         //
         verifyException("org.example.AtivRepeticoes", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      BenchPress benchPress0 = null;
      try {
        benchPress0 = new BenchPress((BenchPress) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.Atividade", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.now();
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), (-29), (-29));
      BenchPress benchPress1 = new BenchPress(benchPress0);
      boolean boolean0 = benchPress1.equals(benchPress0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress();
      boolean boolean0 = benchPress0.equals((Object) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.now();
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), (-29), (-29));
      boolean boolean0 = benchPress0.equals(benchPress0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      LocalTime localTime0 = MockLocalTime.now();
      LocalDateTime localDateTime0 = MockLocalDateTime.now();
      BenchPress benchPress0 = new BenchPress(localDateTime0, localTime0, (-29), (-29), (-29));
      boolean boolean0 = benchPress0.equals(localTime0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      BenchPress benchPress0 = new BenchPress((LocalDateTime) null, (LocalTime) null, (-508), (-508), 0.8);
      // Undeclared exception!
      try { 
        benchPress0.toString();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.Atividade", e);
      }
  }
}
