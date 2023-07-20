-- POPULANDO TABELA rebelde
INSERT INTO rebelde (nome, genero, idade, status, localizacao_id)
VALUES
  ('Luke Skywalker', 'Masculino', 25, true, 1),
  ('Leia Organa', 'Feminino', 23, true, 2),
  ('Han Solo', 'Masculino', 30, true, 3),
  ('Chewbacca', 'Masculino', 50, true, 4),
  ('Yoda', 'Masculino', 900, true, 5);

-- Inserindo o inventário e o relatório para cada rebelde
INSERT INTO inventario (rebelde_id) VALUES (1);
INSERT INTO inventario (rebelde_id) VALUES (2);
INSERT INTO inventario (rebelde_id) VALUES (3);
INSERT INTO inventario (rebelde_id) VALUES (4);
INSERT INTO inventario (rebelde_id) VALUES (5);

INSERT INTO relatorio (rebelde_id) VALUES (1);
INSERT INTO relatorio (rebelde_id) VALUES (2);
INSERT INTO relatorio (rebelde_id) VALUES (3);
INSERT INTO relatorio (rebelde_id) VALUES (4);
INSERT INTO relatorio (rebelde_id) VALUES (5);

INSERT INTO inventario_produto (inventario_id, produto_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (2, 4),
  (3, 1),
  (3, 3),
  (4, 2),
  (4, 4),
  (5, 1),
  (5, 2);


SELECT * FROM rebelde r ;
SELECT * FROM localizacao ;
SELECT * FROM produto p ;
SELECT * FROM inventario i ;
SELECT * FROM relatorio r  ;
SELECT * FROM inventario_produto ip ;
