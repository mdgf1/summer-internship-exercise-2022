Está aqui a minha proposta de solução para este problema,

Trata-se de uma espécie de Breadth-first search que procura passar por todas as combinações de códigos possíveis com a condição de paragem dada pelo tamanho do caminho percurrido.

Nunca tinha utilizado a interface Future e demorei bastante tempo a entender como utiliza-la, quase tanto tempo como a escrever código em sí mas pronto :)

O problema é interessante e talvez exista uma solução mais rápida e elegante mas esta foi a que pensei

Pesquisei na internet e rapidamente encontrei uma solução para um problema parecido no geeksforgeeks, tentei não olhar muito para ela mas contaminou-me a solução final na função getJump() ao calcular os conflitos entre casas.

Tentei fazer uma solução mudelar que desse para expandir para matrixes 4x4 e 5x5 mas apercebi-me que os conflitos entre casas crescem muito rapidamente o 
que deixa de fazer sentido fazer os conflitos todos 1 a 1 linha a linha.

Obrigado por ler,
Miguel Fonseca

Update:

Fiz uma solução mudelar e resolvi todos os conflitos em códigos de n por n,
No entanto existe um crescimento exponencial de códigos possíveis, o que faz com que se torne impossível com o meu algoritmo a testagem de qualquer tipo de problema a partir de um certo tamanho
pois todos os testes passam a dar Timeout error, o tamanho máximo que consegui testar em todos os codigos foi:
	
	tamanho do codigo || tamanho do tabuleiro

			9					3
			6					4
			5 					5
			4 					6
			4 					7
			4 					8
			4 					9
			3 					10
			3 					11
			3 					12
			3 					13
			...					...

provavelmente existem multiplas soluções mais eficientes e existem até maneiras de tornar a minha solução mais eficiente, no entanto sinto que esta solução é boa o suficiente para o problema pedido
