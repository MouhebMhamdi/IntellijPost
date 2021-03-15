-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 26 mars 2020 à 21:49
-- Version du serveur :  5.6.10
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pfe`
--

-- --------------------------------------------------------

--
-- Structure de la table `archive`
--

DROP TABLE IF EXISTS `archive`;
CREATE TABLE IF NOT EXISTS `archive` (
  `id` bigint(20) NOT NULL,
  `cin` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgdr9js865h52k69ino8tqmrts` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `archive`
--

INSERT INTO `archive` (`id`, `cin`, `date`, `montant`, `nom`, `user_id`) VALUES
(7, 11946317, '2020-03-25', 500, 'Emmission Mondat Ordinaire', 1),
(11, 12345678, '2020-03-26', 150, 'Paiement Mondat', 3),
(12, 11946317, '2020-03-26', 6458, 'Crée Epargne', 3),
(13, 11946314, '2020-03-26', 160, 'Retrait Epargne', 3),
(14, 11946317, '2020-03-26', 1605, 'western union', 3);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4513),
(4513),
(4513),
(4513);

-- --------------------------------------------------------

--
-- Structure de la table `pointage`
--

DROP TABLE IF EXISTS `pointage`;
CREATE TABLE IF NOT EXISTS `pointage` (
  `id` int(11) NOT NULL,
  `cas` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7uhab3gu6nfpny9otd5fmfbjb` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pointage`
--

INSERT INTO `pointage` (`id`, `cas`, `date`, `user_id`) VALUES
(5, 1, '2020-03-25', 3),
(10, 1, '2020-03-25', 8),
(19, 1, '2020-03-26', 17),
(4512, 0, '2020-03-26', 15);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(2, 'ROLE_ADMIN'),
(4, 'ROLE_USER'),
(9, 'ROLE_USER'),
(16, 'ROLE_USER'),
(18, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cin` bigint(20) NOT NULL,
  `code_secret` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `guichet` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `tel` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `adresse`, `cin`, `code_secret`, `email`, `guichet`, `nom`, `password`, `prenom`, `tel`, `username`) VALUES
(1, 'rue monji slim', 11946317, 'mouheb', 'mouhebmh@gmail.com', 1, 'mhamdi', '$2a$10$wAiIDbqej0q7oSBlwMJTyueDgt5CSsPeF.ZgDfGu7f0s2kfcGNZd.', 'mouheb', 50566033, 'admin'),
(3, 'rue monji slim', 11946317, '1234', 'eya.wafi@gmail.com', 1, 'wefi', '$2a$10$MblrVvwK4mgtPDtGRgYeE.sFDGrsp.bPuY0BRs1EnVBSHmNA2uJDi', 'eya', 50219558, 'user'),
(8, 'rue monji slim ', 11946317, '1234', 'user@gmail.com', 1, 'mhamdi', '$2a$10$9JjoA3kTsGamy9cEmqJ.luZaG1InYL80jx3yP9GMShZV18dFJ7u3a', 'mouheb', 50566033, 'user1'),
(15, 'rue monji slim ', 11946317, '1234', 'mouhebmh@gmail.com', 2, 'azazaz', '$2a$10$wNx5bSDVvcfokJmuD2vhCutpXfYEKdUtC1SyE/VorSb6c7RPxqahu', 'azaza', 50566033, 'azaza'),
(17, 'rue monji slim ', 11946317, '1234', 'user@gmail.com', 3, 'mhamdi', '$2a$10$/bwvQOv5GSK1moLCpAnqruO6BS09Dzs/1HMQUuyhcK3iuM8H4Uace', 'mouheb', 50566033, 'user2');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(3, 4),
(8, 9),
(15, 16),
(17, 18);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `archive`
--
ALTER TABLE `archive`
  ADD CONSTRAINT `FKgdr9js865h52k69ino8tqmrts` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `pointage`
--
ALTER TABLE `pointage`
  ADD CONSTRAINT `FK7uhab3gu6nfpny9otd5fmfbjb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
