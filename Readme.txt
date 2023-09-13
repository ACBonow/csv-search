# CSV Search - Java 17 Application

## Visão Geral

CSV Search é uma aplicação Java 17 que permite filtrar e processar dados de um arquivo CSV com base em coordenadas geográficas e outros critérios. Ele utiliza threads para processamento paralelo e fornece uma saída formatada para fácil análise.

## Requisitos

Certifique-se de que você possui o seguinte software instalado em seu sistema:

- Java Development Kit (JDK) 17 ou superior: [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Apache Maven (opcional, para compilação e gerenciamento de dependências): [Download Maven](https://maven.apache.org/download.cgi)

    ## Configuração

    1. Clone este repositório para o seu sistema:


   git clone https://github.com/seu-usuario/csv-search.git

    2. Uso pelo IntelliJ IDEA
            Nessa opção ele ja vai configurado para rodar com uma linha de comando no padrão estabelecido/
        2.1. Abra o projeto no IntelliJIDEA;
        2.2. Contrua o projeto (Build atalho -> Ctrl + F9);
        2.3. Vá em File/Run/Edit Configuration
            Adicionando LINHA DE COMANDO
            - Click no "+" para adicionar nova configuração;
            - Escolha "Aplication" na lista de opções
            - Vai estar essa opção como parâmetro "./csv-search --location -23.72201,-046.59195"

            *caso opte por alterar pelo arquivo vá em jetbrains://idea/navigate/reference?project=csv-search&path=~\IdeaProjects\csv-search\.idea\runConfigurations\Main.xml
            na linha 5 terá a opção  <option name="PROGRAM_PARAMETERS" value="./csv-search --location -23.72201,-046.59195" />
            onde "value" você coloca a linha de comando de entrada.
        2.4. Rode o projeto (Run atalho -> Ctrl + f10);


    3. No Terminal
        3.1. Navegue até o diretório do projeto:
                cd ..seuCaminho/csv-search
        3.2 Compile o programa (se você não possui o Maven instalado, compile manualmente usando o JDK):
                mvn compile

    4 Uso
Para executar o programa, você precisa fornecer as coordenadas geográficas e o arquivo CSV que deseja processar. Use o seguinte comando como exemplo:
        exemplo: java -jar target/csv-search.jar --location <latitude>,<longitude>

Substitua <latitude> e <longitude> pelas coordenadas geográficas desejadas. Certifique-se de que o arquivo CSV esteja presente na pasta src/main/resources com o nome eventlog.csv.
exemplo:

Resultados
Os resultados do processamento serão exibidos no console, mostrando a quantidade de eventos no raio das coordenadas estabelecidas e os detalhes dos eventos filtrados, incluindo dispositivo, distância, instante e payload.
*TAMBÉM* irão ser salvos em um arquivo no local "seuCaminho\csv-search\resultado.csv"

Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.

