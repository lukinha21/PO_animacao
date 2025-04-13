# 🧩 Animação de Métodos de Ordenação — JavaFX

> Trabalho acadêmico de Estrutura de Dados com foco em **visualização e animação gráfica de algoritmos de ordenação** utilizando **JavaFX**.

---

## 🎯 Objetivo

O projeto simula graficamente a execução de **dois algoritmos de ordenação** definidos pelo número final do RA, conforme especificação do professor. A animação inclui:

- Geração aleatória de valores
- Visualização gráfica dos elementos (como botões) sendo comparados e permutados
- Destaque de linhas do código-fonte que está sendo executado
- Uso de **setas, cores e indicadores** para guiar a compreensão do algoritmo

---

## 🧪 Algoritmos implementados

✅ Exemplo:

- `2 –  Quick Sort sem pivô`  

---

## 💻 Tecnologias Utilizadas

- Java 11+
- JavaFX (`AnchorPane`, `Button`, `Platform.runLater`)
- Threads e `Task` para animação assíncrona
- Controle manual de coordenadas (X/Y) para simular movimentações

---

## 🎬 Exemplo da Animação

Os elementos são representados por **botões numerados**, que são reposicionados na tela durante as trocas, simulando a execução do algoritmo passo a passo:

```java
for (int i = 0; i < 10; i++) {
    Platform.runLater(() -> vet[0].setLayoutY(vet[0].getLayoutY() + 5));
    Thread.sleep(50);
}
Além disso, o código-fonte do algoritmo é mostrado visualmente com realce da linha ativa durante a execução.

📁 Estrutura
bash
Copiar
Editar
/src
├── Quick.java        # Algoritmo 1 (exemplo) , Interface principal e inicialização JavaFX
▶️ Como executar
Compile com suporte ao JavaFX:


💡 Se estiver usando uma IDE como IntelliJ ou VSCode, basta configurar o SDK JavaFX no projeto.

🤝 Autores
Trabalho desenvolvido para a disciplina de Pesquisa e Ordenação – Prof. Francisco Assis da Silva

Alunos:

Lucas Alexandre

🧠 Créditos
Baseado na estrutura fornecida no exemplo do professor, com uso de Task<Void> e Platform.runLater() para animações sincronizadas.

📎 Licença
Uso acadêmico e educacional. Livre para estudo.
