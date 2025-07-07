CREATE TABLE etudiant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO etudiant (nom, email) VALUES ('Ali', 'ali@example.com');
INSERT INTO etudiant (nom, email) VALUES ('Sara', 'sara@example.com');
