## Como executar:

### Com o código fonte

* Compile todo o código:

	>`javac *.java`

* Execute o _.class_ da sua classe principal com o nome do arquivo se o arquivo está na mesma pasta que os seus arquivos _.class_:

	>`java Main entrada.txt`

* Observe que se arquivo de entrada não estiver na mesma pasta a última parte deve ser o caminho do arquivo (absoluto ou relativo) como no exemplo:

	>`java Main /home/fulano/entrada.txt`

### Com um arquivo jar executável
- Comando para criar o _programa.jar_ a partir do código fonte: 

	>`java cvf programa.jar *.clas`

- Dentro da pasta com o _.jar_ e o arquivo de entrada você deve abrir o terminal e executar o comando abaixo se o arquivo está na mesma pasta que o _.jar_:

	>`java -jar programa.jar entrada.txt`

- Observe que se arquivo não estiver na mesma pasta, a última parte deve ser o caminho do arquivo (absoluto ou relativo):

	>`java -jar programa.jar /home/fulano/entrada.txt`


