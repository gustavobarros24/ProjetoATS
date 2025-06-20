/*
 * This file was automatically generated by EvoSuite
 * Wed Jun 04 11:51:19 GMT 2025
 */

package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.chrono.IsoChronology;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.time.MockClock;
import org.evosuite.runtime.mock.java.time.MockLocalDate;
import org.evosuite.runtime.mock.java.time.chrono.MockIsoChronology;
import org.example.UtilizadorProfissional;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class UtilizadorProfissional_ESTest extends UtilizadorProfissional_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      Period period0 = Period.ZERO;
      ZoneOffset zoneOffset0 = ZoneOffset.MIN;
      Clock clock0 = MockClock.system(zoneOffset0);
      LocalDate localDate0 = MockLocalDate.now(clock0);
      IsoChronology isoChronology0 = period0.getChronology();
      LocalDate localDate1 = MockIsoChronology.dateNow(isoChronology0);
      Object object0 = utilizadorProfissional0.utilizadorNumPeriodo(localDate0, localDate1);
      assertNotSame(object0, utilizadorProfissional0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      // Undeclared exception!
      try { 
        utilizadorProfissional0.utilizadorNumPeriodo((LocalDate) null, (LocalDate) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // date
         //
         verifyException("java.util.Objects", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional("+h52M%]K,2j?F7*", "+h52M%]K,2j?F7*", "+a|}>Jk5P(Ow", 1, (-2037), (-3199), (LocalDate) null, 'i');
      // Undeclared exception!
      try { 
        utilizadorProfissional0.toString();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.Utilizador", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = null;
      try {
        utilizadorProfissional0 = new UtilizadorProfissional((UtilizadorProfissional) null, (LocalDate) null, (LocalDate) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.Utilizador", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = null;
      try {
        utilizadorProfissional0 = new UtilizadorProfissional((UtilizadorProfissional) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.example.Utilizador", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      UtilizadorProfissional utilizadorProfissional1 = new UtilizadorProfissional(utilizadorProfissional0);
      assertEquals("", utilizadorProfissional1.getNome());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      LocalDate localDate0 = MockLocalDate.ofYearDay(209, 209);
      UtilizadorProfissional utilizadorProfissional1 = new UtilizadorProfissional(utilizadorProfissional0, localDate0, localDate0);
      assertEquals("", utilizadorProfissional1.getMorada());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      UtilizadorProfissional utilizadorProfissional1 = new UtilizadorProfissional();
      boolean boolean0 = utilizadorProfissional0.equals(utilizadorProfissional1);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay((-900));
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional("`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", (-900), (-900), (-900), localDate0, 'D');
      boolean boolean0 = utilizadorProfissional0.equals((Object) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay((-900));
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional("`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", (-900), (-900), (-900), localDate0, 'D');
      boolean boolean0 = utilizadorProfissional0.equals(localDate0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional();
      boolean boolean0 = utilizadorProfissional0.equals(utilizadorProfissional0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay((-900));
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional("`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", (-900), (-900), (-900), localDate0, 'D');
      double double0 = utilizadorProfissional0.getFatorMultiplicativo();
      assertEquals(1.5, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay((-900));
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional("`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", (-900), (-900), (-900), localDate0, 'D');
      String string0 = utilizadorProfissional0.toString();
      assertEquals("Utilizador\nCodigo de Utilizador: 1\nNome: `\"lc\"&\"Q:,\"o1\nMorada: `\"lc\"&\"Q:,\"o1\nEmail: `\"lc\"&\"Q:,\"o1\nFrequencia Cardiaca: -900 bpm\nPeso: -900.0 kilos\nAltura: -900 centimetros\nData de nascimento 16/07/1967\nIdade: 47 anos\nGenero: D\nAtividades: \n\nPlanos de treino: \n\nTipo de Utilizador: Profissional\n", string0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      LocalDate localDate0 = MockLocalDate.ofEpochDay((-900));
      UtilizadorProfissional utilizadorProfissional0 = new UtilizadorProfissional("`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", "`\"lc\"&\"Q:,\"o1", (-900), (-900), (-900), localDate0, 'D');
      Object object0 = utilizadorProfissional0.clone();
      assertNotSame(object0, utilizadorProfissional0);
  }
}
