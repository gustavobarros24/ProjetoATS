# ProjetoATS
Projeto no âmbito da UC Análise e Testes de Software

# <p style="text-align: center;">To Do</p>

## Plano de ATS:

- [x] Refactor dos projetos para Maven

### Projeto1:

- [x] Fazer testes unitários dos MODELOS (Classes abstratas são sempre testadas através das subclasses btw).
- [x] Usar EvoSuite para geração de testes automáticos.
- [x] Analisar cobertura dos testes (isto será mais para o relatório).
- [x] Usar PIT para criar mutantes.
- [ ] Usar Quickcheck ou Hypothesis para criar generators.

### ProjetoPOO:

- [x] Fazer testes unitários dos MODELOS (Classes abstratas são sempre testadas através das subclasses btw).
- [x] Usar EvoSuite para geração de testes automáticos.
- [x] Analisar cobertura dos testes (isto será mais para o relatório).
- [x] Usar PIT para criar mutantes.
- [ ] Usar Quickcheck ou Hypothesis para criar generators.

### Projeto2:

- [x] Fazer testes unitários dos MODELOS (Classes abstratas são sempre testadas através das subclasses btw).
- [ ] Usar EvoSuite para geração de testes automáticos.
- [x] Analisar cobertura dos testes (isto será mais para o relatório).
- [x] Usar PIT para criar mutantes.
- [x] Usar Quickcheck ou Hypothesis para criar generators.

### Extras

- [x] Usar Maven para correr os testes e etc....
- [x] Utilizar LLMs para geração de testes
- [x] Usar uma ferramenta de property based testing de Java (jqwik)

### Relatório

- [x] Relatório

## Instruções

Usar o terminal e executar os seguintes comandos:

- Usar "mvn clean test" para correr os testes em cada projeto.

- Usar "mvn org.pitest:pitest-maven:mutationCoverage" para gerar os relatórios PIT.

- Usar "mvn clean verify" para gerar os relatórios JaCoCo.

- Consultar tudo na pasta target.