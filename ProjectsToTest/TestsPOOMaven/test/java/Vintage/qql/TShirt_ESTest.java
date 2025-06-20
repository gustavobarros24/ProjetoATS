/*
 * This file was automatically generated by EvoSuite
 * Wed Jun 04 15:29:12 GMT 2025
 */

package Vintage.qql;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import Vintage.qql.TShirt;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class TShirt_ESTest extends TShirt_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      TShirt tShirt1 = new TShirt(tShirt0);
      tShirt1.setDesconto((-30));
      boolean boolean0 = tShirt0.equals(tShirt1);
      assertEquals((-30), tShirt1.getDesconto());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPadrao("PALMEIRAS");
      TShirt.Padrao tShirt_Padrao0 = tShirt0.getPadrao();
      assertEquals(0, tShirt0.getDesconto());
      assertEquals(TShirt.Padrao.PALMEIRAS, tShirt_Padrao0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setDesconto(1614);
      int int0 = tShirt0.getDesconto();
      assertEquals(1614, int0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setDesconto((-1877420743));
      int int0 = tShirt0.getDesconto();
      assertEquals((-1877420743), int0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setStock(567);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setStock((-436));
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPrecoFinal(1.0F);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPrecoFinal((-1107.2573F));
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPrecoBase(1.0F);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPrecoBase((-517.83F));
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setNumDonos(1223);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setNumDonos((-24));
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setNovo(false);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setDesconto(83);
      tShirt0.clone();
      assertEquals(83, tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setDesconto((-1877420743));
      tShirt0.clone();
      assertEquals((-1877420743), tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setCustosExpedicao(83);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setCustosExpedicao((-1877420743));
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setCorrecaoPreco(685.02F);
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setCorrecaoPreco((-4412));
      TShirt tShirt1 = tShirt0.clone();
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPrecoBase((-102.0F));
      float float0 = tShirt0.calcularPrecoFinal();
      assertEquals((-102.0F), tShirt0.getPrecoFinal(), 0.01F);
      assertEquals((-102.0F), float0, 0.01F);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      // Undeclared exception!
      try { 
        tShirt0.setTamanho((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("Vintage.qql.TShirt", e);
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      // Undeclared exception!
      try { 
        tShirt0.setPadrao((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("Vintage.qql.TShirt", e);
      }
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Integer integer0 = new Integer(0);
      TShirt tShirt0 = null;
      try {
        tShirt0 = new TShirt("QMW6aX>*84K'?p:)u", ".+P9Z\nm;E%`f!a", (String) null, integer0, 0, 2102.7273F, false, (-1453), "iCS&", "d=[k5R", (-1453), "d=[k5R", (String) null, "QMW6aX>*84K'?p:)u", 0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("Vintage.qql.TShirt", e);
      }
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      TShirt tShirt0 = null;
      try {
        tShirt0 = new TShirt((TShirt) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("Vintage.qql.Artigo", e);
      }
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      TShirt tShirt0 = new TShirt("W?", "RISCAS", "4&at}", (Integer) null, 0.5F, 926.5811F, false, (-2415), "W?", "4&at}", (-2415), "", "S", "RISCAS", (-2415));
      tShirt0.setPadrao("RISCAS");
      assertEquals(TShirt.Padrao.RISCAS, tShirt0.getPadrao());
      assertEquals(TShirt.Tamanho.S, tShirt0.getTamanho());
      assertEquals((-2415), tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      // Undeclared exception!
      try { 
        tShirt0.setPadrao("Y^rHw|ra?Wd");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Invalid padrao: Y^rHw|ra?Wd
         //
         verifyException("Vintage.qql.TShirt", e);
      }
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      // Undeclared exception!
      try { 
        tShirt0.setTamanho("nC2,vu@66&&I)");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Invalid tamanho: nC2,vu@66&&I)
         //
         verifyException("Vintage.qql.TShirt", e);
      }
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setTamanho("M");
      assertEquals(TShirt.Tamanho.M, tShirt0.getTamanho());
      assertEquals(0, tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      int int0 = tShirt0.getDesconto();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.getPadrao();
      assertEquals(0, tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.getTamanho();
      assertEquals(0, tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      TShirt tShirt1 = new TShirt();
      tShirt0.setDesconto((-2826));
      boolean boolean0 = tShirt0.equals(tShirt1);
      assertEquals((-2826), tShirt0.getDesconto());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      TShirt tShirt0 = new TShirt("W?", "RISCAS", "4&at}", (Integer) null, 0.5F, 926.5811F, false, (-2415), "W?", "4&at}", (-2415), "", "S", "RISCAS", (-2415));
      TShirt tShirt1 = new TShirt(tShirt0);
      boolean boolean0 = tShirt0.equals(tShirt1);
      assertEquals(TShirt.Tamanho.S, tShirt1.getTamanho());
      assertTrue(boolean0);
      assertEquals((-2415), tShirt1.getDesconto());
      assertEquals(TShirt.Padrao.RISCAS, tShirt1.getPadrao());
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      TShirt tShirt1 = new TShirt(tShirt0);
      tShirt1.setNumDonos(1);
      boolean boolean0 = tShirt1.equals(tShirt0);
      assertEquals(0, tShirt1.getDesconto());
      assertFalse(tShirt1.equals((Object)tShirt0));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      boolean boolean0 = tShirt0.equals("");
      assertFalse(boolean0);
      assertEquals(0, tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test35()  throws Throwable  {
      TShirt tShirt0 = new TShirt("W?", "RISCAS", "4&at}", (Integer) null, 0.5F, 926.5811F, false, (-2415), "W?", "4&at}", (-2415), "", "S", "RISCAS", (-2415));
      boolean boolean0 = tShirt0.equals(tShirt0);
      assertTrue(boolean0);
      assertEquals(TShirt.Tamanho.S, tShirt0.getTamanho());
      assertEquals((-2415), tShirt0.getDesconto());
      assertEquals(TShirt.Padrao.RISCAS, tShirt0.getPadrao());
  }

  @Test(timeout = 4000)
  public void test36()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      TShirt tShirt1 = new TShirt(tShirt0);
      assertTrue(tShirt1.equals((Object)tShirt0));
      
      tShirt0.setTamanho("S");
      boolean boolean0 = tShirt1.equals(tShirt0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test37()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      float float0 = tShirt0.calcularPrecoFinal();
      assertEquals(0.0F, float0, 0.01F);
      assertEquals(0.0F, tShirt0.getPrecoFinal(), 0.01F);
      assertEquals(0, tShirt0.getDesconto());
  }

  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setPadrao("PALMEIRAS");
      TShirt tShirt1 = new TShirt();
      boolean boolean0 = tShirt0.equals(tShirt1);
      assertFalse(boolean0);
      assertEquals(TShirt.Padrao.PALMEIRAS, tShirt0.getPadrao());
      assertEquals(0, tShirt1.getDesconto());
  }

  @Test(timeout = 4000)
  public void test39()  throws Throwable  {
      TShirt tShirt0 = new TShirt("W?", "RISCAS", "4&at}", (Integer) null, 0.5F, 926.5811F, false, (-2415), "W?", "4&at}", (-2415), "", "S", "RISCAS", (-2415));
      float float0 = tShirt0.calcularPrecoFinal();
      assertEquals(0.25F, tShirt0.getPrecoFinal(), 0.01F);
      assertEquals(0.25F, float0, 0.01F);
  }

  @Test(timeout = 4000)
  public void test40()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setTamanho("XL");
      assertEquals(0, tShirt0.getDesconto());
      assertEquals(TShirt.Tamanho.XL, tShirt0.getTamanho());
  }

  @Test(timeout = 4000)
  public void test41()  throws Throwable  {
      Integer integer0 = new Integer(1);
      TShirt tShirt0 = null;
      try {
        tShirt0 = new TShirt("=C+VgW9__tAvEiX5", "=C+VgW9__tAvEiX5", "=C+VgW9__tAvEiX5", integer0, 1, 1, false, 1, "=C+VgW9__tAvEiX5", "=C+VgW9__tAvEiX5", 1, "=C+VgW9__tAvEiX5", "M", "M", 1);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Invalid padrao: M
         //
         verifyException("Vintage.qql.TShirt", e);
      }
  }

  @Test(timeout = 4000)
  public void test42()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      tShirt0.setTamanho("L");
      TShirt.Tamanho tShirt_Tamanho0 = tShirt0.getTamanho();
      assertEquals(0, tShirt0.getDesconto());
      assertEquals(TShirt.Tamanho.L, tShirt_Tamanho0);
  }

  @Test(timeout = 4000)
  public void test43()  throws Throwable  {
      TShirt tShirt0 = new TShirt();
      String string0 = tShirt0.toString();
      assertEquals("c\u00F3digo: 0, nome: , descri\u00E7\u00E3o: , marca: , pre\u00E7o base: 0.0, corre\u00E7\u00E3o de pre\u00E7o: 0.0, novo: true, n\u00FAmero de donos: 0, condi\u00E7\u00E3o: , transportadora: , vendedor: , tipo: t-shirt, tamanho: null, padr\u00E3o: null, desconto: 0%", string0);
  }
}
