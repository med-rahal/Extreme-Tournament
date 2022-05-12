<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220409163810 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE commentaire (id_commentaire INT AUTO_INCREMENT NOT NULL, id_publication INT NOT NULL, id_user INT NOT NULL, text VARCHAR(150) NOT NULL, date_comment DATE NOT NULL, nbr_reports INT NOT NULL, INDEX IDX_67F068BCB72EAA8E (id_publication), INDEX IDX_67F068BC6B3CA4B (id_user), PRIMARY KEY(id_commentaire)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE equipe (nom_equipe VARCHAR(50) NOT NULL, id_match INT NOT NULL, id_user INT NOT NULL, nb_participants INT NOT NULL, image VARCHAR(200) NOT NULL, categorie VARCHAR(40) NOT NULL, epass VARCHAR(255) NOT NULL, INDEX IDX_2449BA1594DE8435 (id_match), PRIMARY KEY(nom_equipe)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE forum (id_forum INT AUTO_INCREMENT NOT NULL, id_publication INT NOT NULL, description_f VARCHAR(150) NOT NULL, email VARCHAR(50) NOT NULL, id_commentaire INT NOT NULL, INDEX IDX_852BBECDB72EAA8E (id_publication), PRIMARY KEY(id_forum)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE likess (id_like INT AUTO_INCREMENT NOT NULL, id_publication INT NOT NULL, id_user INT NOT NULL, INDEX IDX_35F308C4B72EAA8E (id_publication), PRIMARY KEY(id_like)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE matchs (id_match INT AUTO_INCREMENT NOT NULL, nom_poule VARCHAR(50) NOT NULL, nom_equipe_a VARCHAR(50) NOT NULL, nom_equipe_b VARCHAR(50) NOT NULL, date_match DATE NOT NULL, emplacement VARCHAR(50) NOT NULL, INDEX IDX_6B1E604150E777D5 (nom_poule), PRIMARY KEY(id_match)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE panier (id_panier INT AUTO_INCREMENT NOT NULL, id_user INT NOT NULL, quantite INT NOT NULL, total_panier DOUBLE PRECISION NOT NULL, INDEX IDX_24CC0DF26B3CA4B (id_user), PRIMARY KEY(id_panier)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE poule (nom_poule VARCHAR(50) NOT NULL, idT INT NOT NULL, INDEX IDX_FA1FEB40D5D012F3 (idT), PRIMARY KEY(nom_poule)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit (ref_prod INT AUTO_INCREMENT NOT NULL, id_panier INT NOT NULL, nom_prod VARCHAR(50) NOT NULL, prix DOUBLE PRECISION NOT NULL, total_en_stock INT NOT NULL, descriptif VARCHAR(50) NOT NULL, categorie_prod VARCHAR(50) NOT NULL, disponibilite VARCHAR(50) NOT NULL, INDEX IDX_29A5EC272FBB81F (id_panier), PRIMARY KEY(ref_prod)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE publication (id_publication INT AUTO_INCREMENT NOT NULL, id_user INT NOT NULL, titre VARCHAR(50) NOT NULL, status VARCHAR(150) NOT NULL, date_creation DATE NOT NULL, image VARCHAR(100) NOT NULL, INDEX IDX_AF3C67796B3CA4B (id_user), PRIMARY KEY(id_publication)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reclamation (id_reclam INT AUTO_INCREMENT NOT NULL, id_user INT NOT NULL, description_r VARCHAR(150) NOT NULL, type VARCHAR(50) NOT NULL, etat_r VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, date_r DATETIME NOT NULL, INDEX IDX_CE6064046B3CA4B (id_user), PRIMARY KEY(id_reclam)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE tournoi (id_t INT AUTO_INCREMENT NOT NULL, nom_t VARCHAR(50) NOT NULL, emplacement_t VARCHAR(50) NOT NULL, date_t DATE NOT NULL, id_user INT NOT NULL, PRIMARY KEY(id_t)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id_user INT AUTO_INCREMENT NOT NULL, nom VARCHAR(50) NOT NULL, prenom VARCHAR(50) NOT NULL, username VARCHAR(50) NOT NULL, date_naissance DATE NOT NULL, sexe VARCHAR(30) NOT NULL, roles VARCHAR(30) NOT NULL, email VARCHAR(50) NOT NULL, passw VARCHAR(255) NOT NULL, tel VARCHAR(30) NOT NULL, adresse VARCHAR(50) NOT NULL, image VARCHAR(255) NOT NULL, PRIMARY KEY(id_user)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCB72EAA8E FOREIGN KEY (id_publication) REFERENCES publication (id_publication)');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BC6B3CA4B FOREIGN KEY (id_user) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE equipe ADD CONSTRAINT FK_2449BA1594DE8435 FOREIGN KEY (id_match) REFERENCES matchs (id_match)');
        $this->addSql('ALTER TABLE forum ADD CONSTRAINT FK_852BBECDB72EAA8E FOREIGN KEY (id_publication) REFERENCES publication (id_publication)');
        $this->addSql('ALTER TABLE likess ADD CONSTRAINT FK_35F308C4B72EAA8E FOREIGN KEY (id_publication) REFERENCES publication (id_publication)');
        $this->addSql('ALTER TABLE matchs ADD CONSTRAINT FK_6B1E604150E777D5 FOREIGN KEY (nom_poule) REFERENCES poule (nom_poule)');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF26B3CA4B FOREIGN KEY (id_user) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE poule ADD CONSTRAINT FK_FA1FEB40D5D012F3 FOREIGN KEY (idT) REFERENCES tournoi (id_t)');
        $this->addSql('ALTER TABLE produit ADD CONSTRAINT FK_29A5EC272FBB81F FOREIGN KEY (id_panier) REFERENCES panier (id_panier)');
        $this->addSql('ALTER TABLE publication ADD CONSTRAINT FK_AF3C67796B3CA4B FOREIGN KEY (id_user) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE6064046B3CA4B FOREIGN KEY (id_user) REFERENCES user (id_user)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE equipe DROP FOREIGN KEY FK_2449BA1594DE8435');
        $this->addSql('ALTER TABLE produit DROP FOREIGN KEY FK_29A5EC272FBB81F');
        $this->addSql('ALTER TABLE matchs DROP FOREIGN KEY FK_6B1E604150E777D5');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCB72EAA8E');
        $this->addSql('ALTER TABLE forum DROP FOREIGN KEY FK_852BBECDB72EAA8E');
        $this->addSql('ALTER TABLE likess DROP FOREIGN KEY FK_35F308C4B72EAA8E');
        $this->addSql('ALTER TABLE poule DROP FOREIGN KEY FK_FA1FEB40D5D012F3');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BC6B3CA4B');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY FK_24CC0DF26B3CA4B');
        $this->addSql('ALTER TABLE publication DROP FOREIGN KEY FK_AF3C67796B3CA4B');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE6064046B3CA4B');
        $this->addSql('DROP TABLE commentaire');
        $this->addSql('DROP TABLE equipe');
        $this->addSql('DROP TABLE forum');
        $this->addSql('DROP TABLE likess');
        $this->addSql('DROP TABLE matchs');
        $this->addSql('DROP TABLE panier');
        $this->addSql('DROP TABLE poule');
        $this->addSql('DROP TABLE produit');
        $this->addSql('DROP TABLE publication');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('DROP TABLE tournoi');
        $this->addSql('DROP TABLE user');
    }
}
