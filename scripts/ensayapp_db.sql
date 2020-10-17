-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-09-2020 a las 02:36:44
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ensayapp_db`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_UserValidation` (`idTipoDoc` INT, `numDoc` DOUBLE, `correoElectronico` VARCHAR(100), OUT `cant` INT, OUT `mensaje` VARCHAR(200))  BEGIN    
    SELECT count(*) INTO cant FROM usuario WHERE id_tipo_doc = idTipoDoc AND num_doc = numDoc;
    IF (cant > 0) THEN
		SET mensaje = "Ya existe un usuario registrado con el tipo y número de identificación ingresados.";
	ELSE
		SELECT count(*) INTO cant FROM usuario WHERE correo LIKE(correoElectronico);
        IF (cant > 0) THEN
			SET mensaje = "El correo electrónico ya se encuentra registrado.";
		ELSE
			SET mensaje = "Es posible realizar el registro";
        END IF;
	END IF;  
    
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `F_GetAverageByRoom` (`roomId` INT) RETURNS DECIMAL(10,2) BEGIN
	DECLARE average DECIMAL(10,2);
    
    SELECT (SUM(calificacion)/COUNT(*)) INTO average 
    FROM reservacion  WHERE id_sala = roomId;
    
    RETURN average;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `F_UserCant` () RETURNS INT(11) BEGIN
	
    DECLARE numUsuarios INT;
    SELECT COUNT(*) INTO numUsuarios FROM usuario;
    RETURN numUsuarios;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activo`
--

CREATE TABLE `activo` (
  `id_activo` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `marca` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `referencia` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado_activo` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'en bodega - en sala',
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion`
--

CREATE TABLE `calificacion` (
  `id_reservacion` int(11) NOT NULL,
  `id_lineamiento_calificacion` int(11) NOT NULL,
  `puntaje` int(11) DEFAULT NULL,
  `comentarios` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id_ciudad` int(11) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='	';

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id_ciudad`, `id_departamento`, `nombre`) VALUES
(1, 5, 'MEDELLIN'),
(2, 8, 'BARRANQUILLA'),
(3, 11, 'BOGOTA, D.C.'),
(4, 13, 'CARTAGENA'),
(5, 15, 'TUNJA'),
(6, 17, 'MANIZALES'),
(7, 18, 'FLORENCIA'),
(8, 19, 'POPAYAN'),
(9, 20, 'VALLEDUPAR'),
(10, 23, 'MONTERIA'),
(11, 25, 'AGUA DE DIOS'),
(12, 27, 'QUIBDO'),
(13, 41, 'NEIVA'),
(14, 44, 'RIOHACHA'),
(15, 47, 'SANTA MARTA'),
(16, 50, 'VILLAVICENCIO'),
(17, 52, 'PASTO'),
(18, 54, 'CUCUTA'),
(19, 63, 'ARMENIA'),
(20, 66, 'PEREIRA'),
(21, 68, 'BUCARAMANGA'),
(22, 70, 'SINCELEJO'),
(23, 73, 'IBAGUE'),
(24, 76, 'CALI'),
(25, 81, 'ARAUCA'),
(26, 85, 'YOPAL'),
(27, 86, 'MOCOA'),
(28, 88, 'SAN ANDRES'),
(29, 91, 'LETICIA'),
(30, 94, 'INIRIDA'),
(31, 95, 'SAN JOSE DEL GUAVIAR'),
(32, 97, 'MITU'),
(33, 99, 'PUERTO CARRE¥O'),
(34, 5, 'ABEJORRAL'),
(35, 54, 'ABREGO'),
(36, 5, 'ABRIAQUI'),
(37, 13, 'ACHI'),
(38, 27, 'ACANDI'),
(39, 41, 'ACEVEDO'),
(40, 50, 'ACACIAS'),
(41, 85, 'AGUAZUL'),
(42, 20, 'AGUACHICA'),
(43, 17, 'AGUADAS'),
(44, 20, 'AGUSTIN CODAZZI'),
(45, 41, 'AGRADO'),
(46, 68, 'AGUADA'),
(47, 85, 'CHAMEZA'),
(48, 95, 'CALAMAR'),
(49, 41, 'AIPE'),
(50, 25, 'ALBAN'),
(51, 52, 'ALBAN'),
(52, 41, 'ALGECIRAS'),
(53, 68, 'ALBANIA'),
(54, 76, 'ALCALA'),
(55, 5, 'ALEJANDRIA'),
(56, 15, 'ALMEIDA'),
(57, 19, 'ALMAGUER'),
(58, 52, 'ALDANA'),
(59, 73, 'ALPUJARRA'),
(60, 27, 'ALTO BAUDO'),
(61, 95, 'EL RETORNO'),
(62, 41, 'ALTAMIRA'),
(63, 73, 'ALVARADO'),
(64, 18, 'ALBANIA'),
(65, 5, 'AMAGA'),
(66, 13, 'ALTOS DEL ROSARIO'),
(67, 47, 'ALGARROBO'),
(68, 73, 'AMBALEMA'),
(69, 5, 'AMALFI'),
(70, 20, 'ASTREA'),
(71, 5, 'ANDES'),
(72, 25, 'ANAPOIMA'),
(73, 44, 'ALBANIA'),
(74, 5, 'ANGELOPOLIS'),
(75, 52, 'ANCUYA'),
(76, 76, 'ANDALUCIA'),
(77, 5, 'ANGOSTURA'),
(78, 5, 'ANORI'),
(79, 25, 'ANOLAIMA'),
(80, 76, 'ANSERMANUEVO'),
(81, 5, 'SANTAFE DE ANTIOQUIA'),
(82, 13, 'ARENAL'),
(83, 17, 'ANSERMA'),
(84, 73, 'ANZOATEGUI'),
(85, 5, 'ANZA'),
(86, 5, 'APARTADO'),
(87, 20, 'BECERRIL'),
(88, 66, 'APIA'),
(89, 15, 'AQUITANIA'),
(90, 17, 'ARANZAZU'),
(91, 19, 'ARGELIA'),
(92, 27, 'ATRATO'),
(93, 5, 'ARBOLETES'),
(94, 15, 'ARCABUCO'),
(95, 52, 'ARBOLEDA'),
(96, 54, 'ARBOLEDAS'),
(97, 68, 'ARATOCA'),
(98, 13, 'ARJONA'),
(99, 25, 'ARBELAEZ'),
(100, 47, 'ARACATACA'),
(101, 76, 'ARGELIA'),
(102, 5, 'ARGELIA'),
(103, 73, 'ARMERO'),
(104, 47, 'ARIGUANI'),
(105, 5, 'ARMENIA'),
(106, 20, 'BOSCONIA'),
(107, 13, 'ARROYOHONDO'),
(108, 81, 'ARAUQUITA'),
(109, 73, 'ATACO'),
(110, 23, 'AYAPEL'),
(111, 27, 'BAGADO'),
(112, 13, 'BARRANCO DE LOBA'),
(113, 19, 'BALBOA'),
(114, 27, 'BAHIA SOLANO'),
(115, 66, 'BALBOA'),
(116, 27, 'BAJO BAUDO'),
(117, 68, 'BARBOSA'),
(118, 8, 'BARANOA'),
(119, 41, 'BARAYA'),
(120, 44, 'BARRANCAS'),
(121, 5, 'BARBOSA'),
(122, 23, 'BUENAVISTA'),
(123, 52, 'BARBACOAS'),
(124, 68, 'BARICHARA'),
(125, 68, 'BARRANCABERMEJA'),
(126, 52, 'BELEN'),
(127, 5, 'BELMIRA'),
(128, 25, 'BELTRAN'),
(129, 15, 'BELEN'),
(130, 5, 'BELLO'),
(131, 17, 'BELALCAZAR'),
(132, 66, 'BELEN DE UMBRIA'),
(133, 15, 'BERBEO'),
(134, 23, 'CANALETE'),
(135, 44, 'DIBULLA'),
(136, 5, 'BETANIA'),
(137, 15, 'BETEITIVA'),
(138, 68, 'BETULIA'),
(139, 5, 'BETULIA'),
(140, 18, 'BELEN DE LOS ANDAQUI'),
(141, 25, 'BITUIMA'),
(142, 15, 'BOAVITA'),
(143, 44, 'DISTRACCION'),
(144, 25, 'BOJACA'),
(145, 27, 'BOJAYA'),
(146, 54, 'BOCHALEMA'),
(147, 19, 'BOLIVAR'),
(148, 76, 'BOLIVAR'),
(149, 5, 'CIUDAD BOLIVAR'),
(150, 68, 'BOLIVAR'),
(151, 15, 'BOYACA'),
(152, 15, 'BRICE¥O'),
(153, 5, 'BRICE¥O'),
(154, 15, 'BUENAVISTA'),
(155, 54, 'BUCARASICA'),
(156, 76, 'BUENAVENTURA'),
(157, 19, 'BUENOS AIRES'),
(158, 44, 'EL MOLINO'),
(159, 50, 'BARRANCA DE UPIA'),
(160, 52, 'BUESACO'),
(161, 70, 'BUENAVISTA'),
(162, 63, 'BUENAVISTA'),
(163, 76, 'GUADALAJARA DE BUGA'),
(164, 5, 'BURITICA'),
(165, 76, 'BUGALAGRANDE'),
(166, 15, 'BUSBANZA'),
(167, 5, 'CACERES'),
(168, 25, 'CABRERA'),
(169, 68, 'CABRERA'),
(170, 76, 'CAICEDONIA'),
(171, 25, 'CACHIPAY'),
(172, 50, 'CABUYARO'),
(173, 70, 'CAIMITO'),
(174, 73, 'CAJAMARCA'),
(175, 5, 'CAICEDO'),
(176, 54, 'CACOTA'),
(177, 85, 'HATO COROZAL'),
(178, 25, 'CAJICA'),
(179, 76, 'CALIMA'),
(180, 54, 'CACHIRA'),
(181, 5, 'CALDAS'),
(182, 19, 'CAJIBIO'),
(183, 63, 'CALARCA'),
(184, 76, 'CANDELARIA'),
(185, 15, 'CALDAS'),
(186, 41, 'CAMPOALEGRE'),
(187, 68, 'CALIFORNIA'),
(188, 5, 'CAMPAMENTO'),
(189, 15, 'CAMPOHERMOSO'),
(190, 27, 'EL CANTON DEL SAN PA'),
(191, 85, 'LA SALINA'),
(192, 8, 'CAMPO DE LA CRUZ'),
(193, 19, 'CALDONO'),
(194, 5, 'CA¥ASGORDAS'),
(195, 85, 'MANI'),
(196, 13, 'CALAMAR'),
(197, 8, 'CANDELARIA'),
(198, 5, 'CARACOLI'),
(199, 19, 'CALOTO'),
(200, 5, 'CARAMANTA'),
(201, 5, 'CAREPA'),
(202, 68, 'CAPITANEJO'),
(203, 76, 'CARTAGO'),
(204, 5, 'EL CARMEN DE VIBORAL'),
(205, 25, 'CAPARRAPI'),
(206, 73, 'CARMEN DE APICALA'),
(207, 5, 'CAROLINA'),
(208, 18, 'CARTAGENA DEL CHAIRA'),
(209, 27, 'CARMEN DEL DARIEN'),
(210, 50, 'CASTILLA LA NUEVA'),
(211, 25, 'CAQUEZA'),
(212, 68, 'CARCASI'),
(213, 73, 'CASABIANCA'),
(214, 5, 'CAUCASIA'),
(215, 25, 'CARMEN DE CARUPA'),
(216, 13, 'CANTAGALLO'),
(217, 27, 'CERTEGUI'),
(218, 68, 'CEPITA'),
(219, 47, 'CERRO SAN ANTONIO'),
(220, 97, 'CARURU'),
(221, 15, 'CERINZA'),
(222, 23, 'CERETE'),
(223, 68, 'CERRITO'),
(224, 85, 'MONTERREY'),
(225, 68, 'CHARALA'),
(226, 23, 'CHIMA'),
(227, 25, 'CHAGUANI'),
(228, 73, 'CHAPARRAL'),
(229, 68, 'CHARTA'),
(230, 47, 'CHIBOLO'),
(231, 66, 'DOSQUEBRADAS'),
(232, 5, 'CHIGORODO'),
(233, 15, 'CHINAVITA'),
(234, 54, 'CHINACOTA'),
(235, 17, 'CHINCHINA'),
(236, 54, 'CHITAGA'),
(237, 20, 'CHIMICHAGUA'),
(238, 25, 'CHIA'),
(239, 15, 'CHIQUINQUIRA'),
(240, 68, 'CHIMA'),
(241, 20, 'CHIRIGUANA'),
(242, 25, 'CHIPAQUE'),
(243, 68, 'CHIPATA'),
(244, 15, 'CHISCAS'),
(245, 25, 'CHOACHI'),
(246, 23, 'CHINU'),
(247, 15, 'CHITA'),
(248, 25, 'CHOCONTA'),
(249, 15, 'CHITARAQUE'),
(250, 15, 'CHIVATA'),
(251, 13, 'CICUCO'),
(252, 15, 'CIENEGA'),
(253, 23, 'CIENAGA DE ORO'),
(254, 47, 'CIENAGA'),
(255, 5, 'CISNEROS'),
(256, 63, 'CIRCASIA'),
(257, 68, 'CIMITARRA'),
(258, 5, 'COCORNA'),
(259, 25, 'COGUA'),
(260, 73, 'COELLO'),
(261, 95, 'MIRAFLORES'),
(262, 52, 'COLON'),
(263, 15, 'COMBITA'),
(264, 70, 'COLOSO'),
(265, 18, 'CURILLO'),
(266, 27, 'CONDOTO'),
(267, 47, 'CONCORDIA'),
(268, 5, 'CONCEPCION'),
(269, 41, 'COLOMBIA'),
(270, 54, 'CONVENCION'),
(271, 52, 'CONSACA'),
(272, 68, 'CONCEPCION'),
(273, 5, 'CONCORDIA'),
(274, 68, 'CONFINES'),
(275, 52, 'CONTADERO'),
(276, 68, 'CONTRATACION'),
(277, 5, 'COPACABANA'),
(278, 13, 'CORDOBA'),
(279, 15, 'COPER'),
(280, 19, 'CORINTO'),
(281, 63, 'CORDOBA'),
(282, 25, 'COTA'),
(283, 15, 'CORRALES'),
(284, 52, 'CORDOBA'),
(285, 70, 'COROZAL'),
(286, 68, 'COROMORO'),
(287, 73, 'COYAIMA'),
(288, 15, 'COVARACHIA'),
(289, 86, 'COLON'),
(290, 81, 'CRAVO NORTE'),
(291, 70, 'COVE¥AS'),
(292, 13, 'CLEMENCIA'),
(293, 15, 'CUBARA'),
(294, 50, 'CUBARRAL'),
(295, 54, 'CUCUTILLA'),
(296, 15, 'CUCAITA'),
(297, 25, 'CUCUNUBA'),
(298, 52, 'CUASPUD'),
(299, 85, 'NUNCHIA'),
(300, 15, 'CUITIVA'),
(301, 50, 'CUMARAL'),
(302, 73, 'CUNDAY'),
(303, 52, 'CUMBAL'),
(304, 20, 'CURUMANI'),
(305, 68, 'CURITI'),
(306, 70, 'CHALAN'),
(307, 85, 'OROCUE'),
(308, 15, 'CHIQUIZA'),
(309, 52, 'CUMBITARA'),
(310, 70, 'EL ROBLE'),
(311, 76, 'DAGUA'),
(312, 5, 'DABEIBA'),
(313, 68, 'EL CARMEN DE CHUCURI'),
(314, 70, 'GALERAS'),
(315, 15, 'CHIVOR'),
(316, 73, 'DOLORES'),
(317, 5, 'DON MATIAS'),
(318, 15, 'DUITAMA'),
(319, 20, 'EL COPEY'),
(320, 54, 'DURANIA'),
(321, 5, 'EBEJICO'),
(322, 52, 'CHACHAGsI'),
(323, 76, 'EL AGUILA'),
(324, 13, 'EL CARMEN DE BOLIVAR'),
(325, 15, 'EL COCUY'),
(326, 41, 'ELIAS'),
(327, 25, 'EL COLEGIO'),
(328, 27, 'EL CARMEN DE ATRATO'),
(329, 47, 'EL BANCO'),
(330, 50, 'EL CALVARIO'),
(331, 54, 'EL CARMEN'),
(332, 68, 'EL GUACAMAYO'),
(333, 76, 'EL CAIRO'),
(334, 18, 'EL DONCELLO'),
(335, 13, 'EL GUAMO'),
(336, 15, 'EL ESPINO'),
(337, 76, 'EL CERRITO'),
(338, 5, 'EL BAGRE'),
(339, 20, 'EL PASO'),
(340, 27, 'EL LITORAL DEL SAN J'),
(341, 52, 'EL CHARCO'),
(342, 54, 'EL TARRA'),
(343, 68, 'EL PE¥ON'),
(344, 76, 'EL DOVIO'),
(345, 85, 'PAZ DE ARIPORO'),
(346, 50, 'EL CASTILLO'),
(347, 52, 'EL PE¥OL'),
(348, 68, 'EL PLAYON'),
(349, 18, 'EL PAUJIL'),
(350, 19, 'EL TAMBO'),
(351, 52, 'EL ROSARIO'),
(352, 25, 'EL PE¥ON'),
(353, 47, 'EL PI¥ON'),
(354, 52, 'EL TABLON DE GOMEZ'),
(355, 25, 'EL ROSAL'),
(356, 52, 'EL TAMBO'),
(357, 54, 'EL ZULIA'),
(358, 85, 'PORE'),
(359, 91, 'EL ENCANTO'),
(360, 5, 'ENTRERRIOS'),
(361, 68, 'ENCINO'),
(362, 70, 'GUARANDA'),
(363, 5, 'ENVIGADO'),
(364, 68, 'ENCISO'),
(365, 13, 'EL PE¥ON'),
(366, 47, 'EL RETEN'),
(367, 73, 'ESPINAL'),
(368, 25, 'FACATATIVA'),
(369, 50, 'EL DORADO'),
(370, 73, 'FALAN'),
(371, 68, 'FLORIAN'),
(372, 15, 'FIRAVITOBA'),
(373, 17, 'FILADELFIA'),
(374, 63, 'FILANDIA'),
(375, 73, 'FLANDES'),
(376, 76, 'FLORIDA'),
(377, 15, 'FLORESTA'),
(378, 68, 'FLORIDABLANCA'),
(379, 25, 'FOMEQUE'),
(380, 44, 'FONSECA'),
(381, 85, 'RECETOR'),
(382, 25, 'FOSCA'),
(383, 5, 'FREDONIA'),
(384, 73, 'FRESNO'),
(385, 5, 'FRONTINO'),
(386, 25, 'FUNZA'),
(387, 50, 'FUENTE DE ORO'),
(388, 52, 'FUNES'),
(389, 25, 'FUQUENE'),
(390, 47, 'FUNDACION'),
(391, 19, 'FLORENCIA'),
(392, 25, 'FUSAGASUGA'),
(393, 15, 'GACHANTIVA'),
(394, 25, 'GACHALA'),
(395, 20, 'GAMARRA'),
(396, 25, 'GACHANCIPA'),
(397, 8, 'GALAPA'),
(398, 15, 'GAMEZA'),
(399, 68, 'GALAN'),
(400, 25, 'GACHETA'),
(401, 41, 'GARZON'),
(402, 68, 'GAMBITA'),
(403, 15, 'GARAGOA'),
(404, 25, 'GAMA'),
(405, 13, 'HATILLO DE LOBA'),
(406, 19, 'GUACHENE'),
(407, 23, 'COTORRA'),
(408, 81, 'FORTUL'),
(409, 85, 'SABANALARGA'),
(410, 63, 'GENOVA'),
(411, 5, 'GIRALDO'),
(412, 41, 'GIGANTE'),
(413, 76, 'GINEBRA'),
(414, 25, 'GIRARDOT'),
(415, 68, 'GIRON'),
(416, 5, 'GIRARDOTA'),
(417, 5, 'GOMEZ PLATA'),
(418, 20, 'GONZALEZ'),
(419, 25, 'GRANADA'),
(420, 5, 'GRANADA'),
(421, 50, 'GRANADA'),
(422, 54, 'GRAMALOTE'),
(423, 5, 'GUADALUPE'),
(424, 85, 'SACAMA'),
(425, 15, 'GUACAMAYAS'),
(426, 25, 'GUACHETA'),
(427, 52, 'GUACHUCAL'),
(428, 5, 'GUARNE'),
(429, 19, 'GUAPI'),
(430, 47, 'GUAMAL'),
(431, 50, 'GUAMAL'),
(432, 66, 'GUATICA'),
(433, 68, 'GUACA'),
(434, 76, 'GUACARI'),
(435, 41, 'GUADALUPE'),
(436, 73, 'GUAMO'),
(437, 25, 'GUADUAS'),
(438, 52, 'GUAITARILLA'),
(439, 68, 'GUADALUPE'),
(440, 86, 'ORITO'),
(441, 5, 'GUATAPE'),
(442, 15, 'GUATEQUE'),
(443, 25, 'GUASCA'),
(444, 68, 'GUAPOTA'),
(445, 52, 'GUALMATAN'),
(446, 25, 'GUATAQUI'),
(447, 68, 'GUAVATA'),
(448, 15, 'GUAYATA'),
(449, 50, 'MAPIRIPAN'),
(450, 85, 'SAN LUIS DE PALENQUE'),
(451, 25, 'GUATAVITA'),
(452, 68, 'GsEPSA'),
(453, 25, 'GUAYABAL DE SIQUIMA'),
(454, 50, 'MESETAS'),
(455, 15, 'GsICAN'),
(456, 25, 'GUAYABETAL'),
(457, 25, 'GUTIERREZ'),
(458, 94, 'BARRANCO MINAS'),
(459, 54, 'HACARI'),
(460, 68, 'HATO'),
(461, 5, 'HELICONIA'),
(462, 54, 'HERRAN'),
(463, 73, 'HERVEO'),
(464, 41, 'HOBO'),
(465, 73, 'HONDA'),
(466, 23, 'LA APARTADA'),
(467, 50, 'LA MACARENA'),
(468, 52, 'ILES'),
(469, 73, 'ICONONZO'),
(470, 5, 'HISPANIA'),
(471, 52, 'IMUES'),
(472, 19, 'INZA'),
(473, 52, 'IPIALES'),
(474, 41, 'IQUIRA'),
(475, 41, 'ISNOS'),
(476, 5, 'ITAGUI'),
(477, 5, 'ITUANGO'),
(478, 27, 'ISTMINA'),
(479, 15, 'IZA'),
(480, 5, 'JARDIN'),
(481, 19, 'JAMBALO'),
(482, 76, 'JAMUNDI'),
(483, 15, 'JENESANO'),
(484, 5, 'JERICO'),
(485, 15, 'JERICO'),
(486, 25, 'JERUSALEN'),
(487, 68, 'JESUS MARIA'),
(488, 50, 'URIBE'),
(489, 68, 'JORDAN'),
(490, 8, 'JUAN DE ACOSTA'),
(491, 25, 'JUNIN'),
(492, 27, 'JURADO'),
(493, 5, 'LA CEJA'),
(494, 15, 'LABRANZAGRANDE'),
(495, 25, 'LA CALERA'),
(496, 54, 'LABATECA'),
(497, 68, 'LA BELLEZA'),
(498, 76, 'LA CUMBRE'),
(499, 41, 'LA ARGENTINA'),
(500, 44, 'HATONUEVO'),
(501, 52, 'LA CRUZ'),
(502, 5, 'LA ESTRELLA'),
(503, 15, 'LA CAPILLA'),
(504, 17, 'LA DORADA'),
(505, 52, 'LA FLORIDA'),
(506, 20, 'LA GLORIA'),
(507, 66, 'LA CELIA'),
(508, 52, 'LA LLANADA'),
(509, 54, 'LA ESPERANZA'),
(510, 68, 'LANDAZURI'),
(511, 25, 'LA MESA'),
(512, 17, 'LA MERCED'),
(513, 5, 'LA PINTADA'),
(514, 52, 'LA TOLA'),
(515, 19, 'LA SIERRA'),
(516, 25, 'LA PALMA'),
(517, 41, 'LA PLATA'),
(518, 19, 'LA VEGA'),
(519, 68, 'LA PAZ'),
(520, 25, 'LA PE¥A'),
(521, 54, 'LA PLAYA'),
(522, 52, 'LA UNION'),
(523, 5, 'LA UNION'),
(524, 20, 'LA JAGUA DE IBIRICO'),
(525, 50, 'LEJANIAS'),
(526, 66, 'LA VIRGINIA'),
(527, 70, 'LA UNION'),
(528, 76, 'LA UNION'),
(529, 85, 'TAMARA'),
(530, 15, 'LA VICTORIA'),
(531, 63, 'LA TEBAIDA'),
(532, 25, 'LA VEGA'),
(533, 15, 'LA UVITA'),
(534, 76, 'LA VICTORIA'),
(535, 52, 'LEIVA'),
(536, 54, 'LOS PATIOS'),
(537, 91, 'LA CHORRERA'),
(538, 68, 'LEBRIJA'),
(539, 15, 'VILLA DE LEYVA'),
(540, 25, 'LENGUAZAQUE'),
(541, 91, 'LA PEDRERA'),
(542, 73, 'LERIDA'),
(543, 18, 'LA MONTA¥ITA'),
(544, 85, 'TAURAMENA'),
(545, 5, 'LIBORINA'),
(546, 52, 'LINARES'),
(547, 73, 'LIBANO'),
(548, 27, 'LLORO'),
(549, 23, 'LORICA'),
(550, 19, 'LOPEZ'),
(551, 52, 'LOS ANDES'),
(552, 54, 'LOURDES'),
(553, 68, 'LOS SANTOS'),
(554, 70, 'LOS PALMITOS'),
(555, 23, 'LOS CORDOBAS'),
(556, 44, 'LA JAGUA DEL PILAR'),
(557, 8, 'LURUACO'),
(558, 5, 'MACEO'),
(559, 15, 'MACANAL'),
(560, 27, 'MEDIO ATRATO'),
(561, 68, 'MACARAVITA'),
(562, 25, 'MACHETA'),
(563, 52, 'MAGsI'),
(564, 70, 'MAJAGUAL'),
(565, 13, 'MAGANGUE'),
(566, 25, 'MADRID'),
(567, 27, 'MEDIO BAUDO'),
(568, 44, 'MAICAO'),
(569, 85, 'TRINIDAD'),
(570, 91, 'LA VICTORIA'),
(571, 68, 'MALAGA'),
(572, 8, 'MALAMBO'),
(573, 13, 'MAHATES'),
(574, 17, 'MANZANARES'),
(575, 52, 'MALLAMA'),
(576, 8, 'MANATI'),
(577, 25, 'MANTA'),
(578, 25, 'MEDINA'),
(579, 5, 'MARINILLA'),
(580, 13, 'MARGARITA'),
(581, 66, 'MARSELLA'),
(582, 85, 'VILLANUEVA'),
(583, 13, 'MARIA LA BAJA'),
(584, 15, 'MARIPI'),
(585, 17, 'MARMATO'),
(586, 20, 'MANAURE'),
(587, 73, 'MARIQUITA'),
(588, 17, 'MARQUETALIA'),
(589, 68, 'MATANZA'),
(590, 17, 'MARULANDA'),
(591, 73, 'MELGAR'),
(592, 19, 'MERCADERES'),
(593, 27, 'MEDIO SAN JUAN'),
(594, 50, 'PUERTO CONCORDIA'),
(595, 15, 'MIRAFLORES'),
(596, 19, 'MIRANDA'),
(597, 66, 'MISTRATO'),
(598, 13, 'MONTECRISTO'),
(599, 18, 'MILAN'),
(600, 47, 'NUEVA GRANADA'),
(601, 91, 'MIRITI - PARANA'),
(602, 73, 'MURILLO'),
(603, 15, 'MONGUA'),
(604, 23, 'MOMIL'),
(605, 68, 'MOGOTES'),
(606, 15, 'MONGUI'),
(607, 23, 'MONTELIBANO'),
(608, 5, 'MONTEBELLO'),
(609, 13, 'MOMPOS'),
(610, 68, 'MOLAGAVITA'),
(611, 15, 'MONIQUIRA'),
(612, 63, 'MONTENEGRO'),
(613, 13, 'MORALES'),
(614, 19, 'MORALES'),
(615, 25, 'MOSQUERA'),
(616, 52, 'MOSQUERA'),
(617, 70, 'MORROA'),
(618, 5, 'MURINDO'),
(619, 15, 'MOTAVITA'),
(620, 18, 'MORELIA'),
(621, 5, 'MUTATA'),
(622, 15, 'MUZO'),
(623, 52, 'NARI¥O'),
(624, 54, 'MUTISCUA'),
(625, 5, 'NARI¥O'),
(626, 25, 'NARI¥O'),
(627, 41, 'NATAGA'),
(628, 73, 'NATAGAIMA'),
(629, 17, 'NEIRA'),
(630, 25, 'NEMOCON'),
(631, 25, 'NILO'),
(632, 25, 'NIMAIMA'),
(633, 5, 'NECOCLI'),
(634, 13, 'NOROSI'),
(635, 52, 'OLAYA HERRERA'),
(636, 15, 'NOBSA'),
(637, 25, 'NOCAIMA'),
(638, 27, 'NOVITA'),
(639, 15, 'NUEVO COLON'),
(640, 5, 'NECHI'),
(641, 17, 'NORCASIA'),
(642, 27, 'NUQUI'),
(643, 76, 'OBANDO'),
(644, 54, 'OCA¥A'),
(645, 68, 'OCAMONTE'),
(646, 15, 'OICATA'),
(647, 23, 'MO¥ITOS'),
(648, 68, 'OIBA'),
(649, 5, 'OLAYA'),
(650, 68, 'ONZAGA'),
(651, 41, 'OPORAPA'),
(652, 73, 'ORTEGA'),
(653, 25, 'VENECIA'),
(654, 52, 'OSPINA'),
(655, 15, 'OTANCHE'),
(656, 70, 'OVEJAS'),
(657, 15, 'PACHAVITA'),
(658, 97, 'PACOA'),
(659, 17, 'PACORA'),
(660, 19, 'PADILLA'),
(661, 25, 'PACHO'),
(662, 15, 'PAEZ'),
(663, 15, 'PAIPA'),
(664, 19, 'PAEZ'),
(665, 20, 'PAILITAS'),
(666, 15, 'PAJARITO'),
(667, 25, 'PAIME'),
(668, 41, 'PAICOL'),
(669, 54, 'PAMPLONA'),
(670, 8, 'PALMAR DE VARELA'),
(671, 52, 'FRANCISCO PIZARRO'),
(672, 54, 'PAMPLONITA'),
(673, 73, 'PALOCABILDO'),
(674, 76, 'PALMIRA'),
(675, 15, 'PANQUEBA'),
(676, 68, 'PALMAR'),
(677, 70, 'PALMITO'),
(678, 17, 'PALESTINA'),
(679, 25, 'PANDI'),
(680, 41, 'PALERMO'),
(681, 68, 'PALMAS DEL SOCORRO'),
(682, 99, 'LA PRIMAVERA'),
(683, 25, 'PARATEBUENO'),
(684, 41, 'PALESTINA'),
(685, 91, 'PUERTO ALEGRIA'),
(686, 15, 'PAUNA'),
(687, 19, 'PATIA'),
(688, 15, 'PAYA'),
(689, 19, 'PIAMONTE'),
(690, 68, 'PARAMO'),
(691, 25, 'PASCA'),
(692, 91, 'PUERTO ARICA'),
(693, 15, 'PAZ DE RIO'),
(694, 52, 'POLICARPA'),
(695, 91, 'PUERTO NARI¥O'),
(696, 5, 'PEÑOL'),
(697, 17, 'PENSILVANIA'),
(698, 47, 'PEDRAZA'),
(699, 15, 'PESCA'),
(700, 5, 'PEQUE'),
(701, 47, 'PIJI¥O DEL CARMEN'),
(702, 68, 'PIEDECUESTA'),
(703, 73, 'PIEDRAS'),
(704, 19, 'PIENDAMO'),
(705, 41, 'PITAL'),
(706, 63, 'PIJAO'),
(707, 8, 'PIOJO'),
(708, 13, 'PINILLOS'),
(709, 68, 'PINCHOTE'),
(710, 15, 'PISBA'),
(711, 20, 'PELAYA'),
(712, 41, 'PITALITO'),
(713, 47, 'PIVIJAY'),
(714, 54, 'PUERTO SANTANDER'),
(715, 23, 'PLANETA RICA'),
(716, 47, 'PLATO'),
(717, 73, 'PLANADAS'),
(718, 8, 'POLONUEVO'),
(719, 8, 'PONEDERA'),
(720, 44, 'MANAURE'),
(721, 52, 'POTOSI'),
(722, 73, 'PRADO'),
(723, 76, 'PRADERA'),
(724, 88, 'PROVIDENCIA'),
(725, 52, 'PROVIDENCIA'),
(726, 50, 'PUERTO GAITAN'),
(727, 86, 'PUERTO ASIS'),
(728, 86, 'PUERTO CAICEDO'),
(729, 20, 'PUEBLO BELLO'),
(730, 23, 'PUEBLO NUEVO'),
(731, 47, 'PUEBLOVIEJO'),
(732, 86, 'PUERTO GUZMAN'),
(733, 15, 'PUERTO BOYACA'),
(734, 25, 'PUERTO SALGAR'),
(735, 66, 'PUEBLO RICO'),
(736, 68, 'PUENTE NACIONAL'),
(737, 8, 'PUERTO COLOMBIA'),
(738, 19, 'PUERTO TEJADA'),
(739, 50, 'PUERTO LOPEZ'),
(740, 52, 'PUERRES'),
(741, 68, 'PUERTO PARRA'),
(742, 86, 'LEGUIZAMO'),
(743, 23, 'PUERTO ESCONDIDO'),
(744, 68, 'PUERTO WILCHES'),
(745, 5, 'PUEBLORRICO'),
(746, 50, 'PUERTO LLERAS'),
(747, 5, 'PUERTO BERRIO'),
(748, 13, 'REGIDOR'),
(749, 15, 'QUIPAMA'),
(750, 23, 'PUERTO LIBERTADOR'),
(751, 25, 'PULI'),
(752, 27, 'RIO IRO'),
(753, 5, 'PUERTO NARE'),
(754, 19, 'PURACE'),
(755, 52, 'PUPIALES'),
(756, 73, 'PURIFICACION'),
(757, 23, 'PURISIMA'),
(758, 50, 'PUERTO RICO'),
(759, 5, 'PUERTO TRIUNFO'),
(760, 81, 'PUERTO RONDON'),
(761, 18, 'PUERTO RICO'),
(762, 25, 'QUEBRADANEGRA'),
(763, 25, 'QUETAME'),
(764, 63, 'QUIMBAYA'),
(765, 66, 'QUINCHIA'),
(766, 25, 'QUIPILE'),
(767, 15, 'RAMIRIQUI'),
(768, 25, 'APULO'),
(769, 54, 'RAGONVALIA'),
(770, 13, 'RIO VIEJO'),
(771, 15, 'RAQUIRA'),
(772, 27, 'RIO QUITO'),
(773, 5, 'REMEDIOS'),
(774, 47, 'REMOLINO'),
(775, 8, 'REPELON'),
(776, 50, 'RESTREPO'),
(777, 76, 'RESTREPO'),
(778, 5, 'RETIRO'),
(779, 18, 'SAN JOSE DEL FRAGUA'),
(780, 25, 'RICAURTE'),
(781, 52, 'RICAURTE'),
(782, 17, 'RIOSUCIO'),
(783, 20, 'RIO DE ORO'),
(784, 5, 'RIONEGRO'),
(785, 27, 'RIOSUCIO'),
(786, 41, 'RIVERA'),
(787, 68, 'RIONEGRO'),
(788, 17, 'RISARALDA'),
(789, 73, 'RIOBLANCO'),
(790, 76, 'RIOFRIO'),
(791, 13, 'SAN CRISTOBAL'),
(792, 15, 'RONDON'),
(793, 20, 'LA PAZ'),
(794, 52, 'ROBERTO PAYAN'),
(795, 19, 'ROSAS'),
(796, 73, 'RONCESVALLES'),
(797, 76, 'ROLDANILLO'),
(798, 73, 'ROVIRA'),
(799, 99, 'SANTA ROSALIA'),
(800, 5, 'SABANALARGA'),
(801, 5, 'SABANETA'),
(802, 15, 'SABOYA'),
(803, 8, 'SABANAGRANDE'),
(804, 8, 'SABANALARGA'),
(805, 15, 'SACHICA'),
(806, 5, 'SALGAR'),
(807, 25, 'SAN ANTONIO DEL TEQU'),
(808, 15, 'SAMACA'),
(809, 5, 'SAN ANDRES DE CUERQU'),
(810, 13, 'SAN ESTANISLAO'),
(811, 5, 'SAN CARLOS'),
(812, 25, 'SAN BERNARDO'),
(813, 13, 'SAN FERNANDO'),
(814, 44, 'SAN JUAN DEL CESAR'),
(815, 5, 'SAN FRANCISCO'),
(816, 17, 'SALAMINA'),
(817, 25, 'SAN CAYETANO'),
(818, 13, 'SAN JACINTO'),
(819, 13, 'SAN JACINTO DEL CAUC'),
(820, 68, 'SABANA DE TORRES'),
(821, 5, 'SAN JERONIMO'),
(822, 13, 'SAN JUAN NEPOMUCENO'),
(823, 5, 'SAN JOSE DE LA MONTA'),
(824, 25, 'SAN FRANCISCO'),
(825, 5, 'SAN JUAN DE URABA'),
(826, 5, 'SAN LUIS'),
(827, 15, 'SAN EDUARDO'),
(828, 23, 'SAHAGUN'),
(829, 27, 'SAN JOSE DEL PALMAR'),
(830, 41, 'SALADOBLANCO'),
(831, 47, 'SABANAS DE SAN ANGEL'),
(832, 54, 'SALAZAR'),
(833, 17, 'SAMANA'),
(834, 25, 'SAN JUAN DE RIO SECO'),
(835, 94, 'MAPIRIPANA'),
(836, 5, 'SAN PEDRO'),
(837, 15, 'SAN JOSE DE PARE'),
(838, 5, 'SAN PEDRO DE URABA'),
(839, 17, 'SAN JOSE'),
(840, 97, 'TARAIRA'),
(841, 5, 'SAN RAFAEL'),
(842, 13, 'SAN MARTIN DE LOBA'),
(843, 15, 'SAN LUIS DE GACENO'),
(844, 41, 'SAN AGUSTIN'),
(845, 68, 'SAN ANDRES'),
(846, 91, 'PUERTO SANTANDER'),
(847, 5, 'SAN ROQUE'),
(848, 13, 'SAN PABLO'),
(849, 23, 'SAN ANDRES SOTAVENTO'),
(850, 54, 'SAN CALIXTO'),
(851, 70, 'SAMPUES'),
(852, 76, 'SAN PEDRO'),
(853, 73, 'SALDA¥A'),
(854, 23, 'SAN ANTERO'),
(855, 13, 'SANTA CATALINA'),
(856, 15, 'SAN MATEO'),
(857, 54, 'SAN CAYETANO'),
(858, 68, 'SAN BENITO'),
(859, 5, 'SAN VICENTE'),
(860, 8, 'SANTA LUCIA'),
(861, 23, 'SAN BERNARDO DEL VIE'),
(862, 47, 'SALAMINA'),
(863, 73, 'SAN ANTONIO'),
(864, 15, 'SAN MIGUEL DE SEMA'),
(865, 41, 'SANTA MARIA'),
(866, 23, 'SAN CARLOS'),
(867, 52, 'SAMANIEGO'),
(868, 70, 'SAN BENITO ABAD'),
(869, 73, 'SAN LUIS'),
(870, 5, 'SANTA BARBARA'),
(871, 68, 'SAN GIL'),
(872, 50, 'SAN CARLOS DE GUAROA'),
(873, 54, 'SANTIAGO'),
(874, 15, 'SAN PABLO DE BORBUR'),
(875, 66, 'SANTA ROSA DE CABAL'),
(876, 68, 'SAN JOAQUIN'),
(877, 13, 'SANTA ROSA'),
(878, 50, 'SAN JUAN DE ARAMA'),
(879, 52, 'SANDONA'),
(880, 68, 'SAN JOSE DE MIRANDA'),
(881, 8, 'SANTO TOMAS'),
(882, 52, 'SAN BERNARDO'),
(883, 5, 'SANTA ROSA DE OSOS'),
(884, 15, 'SANTANA'),
(885, 23, 'SAN PELAYO'),
(886, 50, 'SAN JUANITO'),
(887, 68, 'SAN MIGUEL'),
(888, 73, 'SANTA ISABEL'),
(889, 52, 'SAN LORENZO'),
(890, 66, 'SANTUARIO'),
(891, 13, 'SANTA ROSA DEL SUR'),
(892, 50, 'SAN MARTIN'),
(893, 68, 'SAN VICENTE DE CHUCU'),
(894, 5, 'SANTO DOMINGO'),
(895, 15, 'SANTA MARIA'),
(896, 63, 'SALENTO'),
(897, 47, 'SAN SEBASTIAN DE BUE'),
(898, 15, 'SANTA ROSA DE VITERB'),
(899, 19, 'SAN SEBASTIAN'),
(900, 52, 'SAN PABLO'),
(901, 52, 'SAN PEDRO DE CARTAGO'),
(902, 15, 'SANTA SOFIA'),
(903, 52, 'SANTA BARBARA'),
(904, 5, 'EL SANTUARIO'),
(905, 19, 'SANTANDER DE QUILICH'),
(906, 52, 'SANTACRUZ'),
(907, 19, 'SANTA ROSA'),
(908, 70, 'SAN JUAN DE BETULIA'),
(909, 47, 'SAN ZENON'),
(910, 68, 'SANTA BARBARA'),
(911, 47, 'SANTA ANA'),
(912, 70, 'SAN MARCOS'),
(913, 20, 'SAN ALBERTO'),
(914, 50, 'VISTAHERMOSA'),
(915, 70, 'SAN ONOFRE'),
(916, 70, 'SAN PEDRO'),
(917, 25, 'SASAIMA'),
(918, 15, 'SATIVANORTE'),
(919, 47, 'SANTA BARBARA DE PIN'),
(920, 52, 'SAPUYES'),
(921, 54, 'SARDINATA'),
(922, 68, 'SANTA HELENA DEL OPO'),
(923, 15, 'SATIVASUR'),
(924, 5, 'SEGOVIA'),
(925, 25, 'SESQUILE'),
(926, 76, 'SEVILLA'),
(927, 81, 'SARAVENA'),
(928, 15, 'SIACHOQUE'),
(929, 25, 'SIBATE'),
(930, 70, 'SAN LUIS DE SINCE'),
(931, 19, 'SILVIA'),
(932, 25, 'SILVANIA'),
(933, 54, 'SILOS'),
(934, 13, 'SIMITI'),
(935, 25, 'SIMIJACA'),
(936, 27, 'SIPI'),
(937, 47, 'SITIONUEVO'),
(938, 68, 'SIMACOTA'),
(939, 86, 'SIBUNDOY'),
(940, 20, 'SAN DIEGO'),
(941, 15, 'SOATA'),
(942, 18, 'SAN VICENTE DEL CAGU'),
(943, 25, 'SOACHA'),
(944, 15, 'SOCOTA'),
(945, 68, 'SOCORRO'),
(946, 86, 'SAN FRANCISCO'),
(947, 5, 'SONSON'),
(948, 18, 'SOLANO'),
(949, 15, 'SOCHA'),
(950, 86, 'SAN MIGUEL'),
(951, 8, 'SOLEDAD'),
(952, 25, 'SOPO'),
(953, 15, 'SOGAMOSO'),
(954, 13, 'SOPLAVIENTO'),
(955, 19, 'SOTARA'),
(956, 86, 'SANTIAGO'),
(957, 5, 'SOPETRAN'),
(958, 15, 'SOMONDOCO'),
(959, 15, 'SORA'),
(960, 15, 'SOTAQUIRA'),
(961, 15, 'SORACA'),
(962, 25, 'SUBACHOQUE'),
(963, 8, 'SUAN'),
(964, 20, 'SAN MARTIN'),
(965, 41, 'SUAZA'),
(966, 68, 'SUAITA'),
(967, 73, 'SUAREZ'),
(968, 70, 'SUCRE'),
(969, 25, 'SUESCA'),
(970, 68, 'SUCRE'),
(971, 99, 'CUMARIBO'),
(972, 15, 'SUSACON'),
(973, 15, 'SUTAMARCHAN'),
(974, 17, 'SUPIA'),
(975, 25, 'SUPATA'),
(976, 97, 'PAPUNAUA'),
(977, 15, 'SUTATENZA'),
(978, 25, 'SUSA'),
(979, 13, 'TALAIGUA NUEVO'),
(980, 19, 'SUAREZ'),
(981, 68, 'SURATA'),
(982, 25, 'SUTATAUSA'),
(983, 18, 'SOLITA'),
(984, 19, 'SUCRE'),
(985, 25, 'TABIO'),
(986, 52, 'TAMINANGO'),
(987, 20, 'TAMALAMEQUE'),
(988, 27, 'TADO'),
(989, 52, 'TANGUA'),
(990, 5, 'TAMESIS'),
(991, 5, 'TARAZA'),
(992, 15, 'TASCO'),
(993, 41, 'TARQUI'),
(994, 5, 'TARSO'),
(995, 25, 'TAUSA'),
(996, 81, 'TAME'),
(997, 25, 'TENA'),
(998, 41, 'TESALIA'),
(999, 15, 'TENZA'),
(1000, 47, 'TENERIFE'),
(1001, 91, 'TARAPACA'),
(1002, 25, 'TENJO'),
(1003, 41, 'TELLO'),
(1004, 27, 'UNGUIA'),
(1005, 54, 'TEORAMA'),
(1006, 41, 'TERUEL'),
(1007, 15, 'TIBANA'),
(1008, 25, 'TIBACUY'),
(1009, 15, 'TIBASOSA'),
(1010, 19, 'TIMBIO'),
(1011, 23, 'TIERRALTA'),
(1012, 25, 'TIBIRITA'),
(1013, 41, 'TIMANA'),
(1014, 15, 'TINJACA'),
(1015, 5, 'TITIRIBI'),
(1016, 19, 'TIMBIQUI'),
(1017, 13, 'TIQUISIO'),
(1018, 15, 'TIPACOQUE'),
(1019, 27, 'UNION PANAMERICANA'),
(1020, 54, 'TIBU'),
(1021, 15, 'TOCA'),
(1022, 25, 'TOCAIMA'),
(1023, 15, 'TOGsI'),
(1024, 25, 'TOCANCIPA'),
(1025, 5, 'TOLEDO'),
(1026, 15, 'TOPAGA'),
(1027, 54, 'TOLEDO'),
(1028, 68, 'TONA'),
(1029, 70, 'SANTIAGO DE TOLU'),
(1030, 19, 'TORIBIO'),
(1031, 15, 'TOTA'),
(1032, 25, 'TOPAIPI'),
(1033, 70, 'TOLU VIEJO'),
(1034, 76, 'TORO'),
(1035, 19, 'TOTORO'),
(1036, 76, 'TRUJILLO'),
(1037, 8, 'TUBARA'),
(1038, 15, 'TUNUNGUA'),
(1039, 76, 'TULUA'),
(1040, 15, 'TURMEQUE'),
(1041, 52, 'SAN ANDRES DE TUMACO'),
(1042, 13, 'TURBACO'),
(1043, 5, 'TURBO'),
(1044, 15, 'TUTA'),
(1045, 13, 'TURBANA'),
(1046, 52, 'TUQUERRES'),
(1047, 15, 'TUTAZA'),
(1048, 25, 'UBALA'),
(1049, 25, 'UBAQUE'),
(1050, 5, 'URAMITA'),
(1051, 15, 'UMBITA'),
(1052, 25, 'VILLA DE SAN DIEGO D'),
(1053, 19, 'VILLA RICA'),
(1054, 25, 'UNE'),
(1055, 76, 'ULLOA'),
(1056, 5, 'URRAO'),
(1057, 44, 'URIBIA'),
(1058, 8, 'USIACURI'),
(1059, 25, 'UTICA'),
(1060, 5, 'VALDIVIA'),
(1061, 73, 'VALLE DE SAN JUAN'),
(1062, 23, 'VALENCIA'),
(1063, 44, 'URUMITA'),
(1064, 68, 'VALLE DE SAN JOSE'),
(1065, 5, 'VALPARAISO'),
(1066, 5, 'VEGACHI'),
(1067, 18, 'VALPARAISO'),
(1068, 5, 'VENECIA'),
(1069, 15, 'VENTAQUEMADA'),
(1070, 68, 'VELEZ'),
(1071, 73, 'VENADILLO'),
(1072, 25, 'VERGARA'),
(1073, 76, 'VERSALLES'),
(1074, 86, 'VALLE DEL GUAMUEZ'),
(1075, 17, 'VICTORIA'),
(1076, 25, 'VIANI'),
(1077, 68, 'VETAS'),
(1078, 76, 'VIJES'),
(1079, 73, 'VILLAHERMOSA'),
(1080, 25, 'VILLAGOMEZ'),
(1081, 54, 'VILLA CARO'),
(1082, 41, 'VILLAVIEJA'),
(1083, 68, 'VILLANUEVA'),
(1084, 5, 'VIGIA DEL FUERTE'),
(1085, 13, 'VILLANUEVA'),
(1086, 17, 'VILLAMARIA'),
(1087, 25, 'VILLAPINZON'),
(1088, 73, 'VILLARRICA'),
(1089, 44, 'VILLANUEVA'),
(1090, 54, 'VILLA DEL ROSARIO'),
(1091, 25, 'VILLETA'),
(1092, 17, 'VITERBO'),
(1093, 25, 'VIOTA'),
(1094, 15, 'VIRACACHA'),
(1095, 94, 'SAN FELIPE'),
(1096, 94, 'PUERTO COLOMBIA'),
(1097, 5, 'YALI'),
(1098, 25, 'YACOPI'),
(1099, 41, 'YAGUARA'),
(1100, 52, 'YACUANQUER'),
(1101, 86, 'VILLAGARZON'),
(1102, 94, 'LA GUADALUPE'),
(1103, 94, 'CACAHUAL'),
(1104, 5, 'YARUMAL'),
(1105, 94, 'PANA PANA'),
(1106, 94, 'MORICHAL'),
(1107, 97, 'YAVARATE'),
(1108, 5, 'YOLOMBO'),
(1109, 76, 'YOTOCO'),
(1110, 76, 'YUMBO'),
(1111, 5, 'YONDO'),
(1112, 13, 'ZAMBRANO'),
(1113, 5, 'ZARAGOZA'),
(1114, 68, 'ZAPATOCA'),
(1115, 76, 'ZARZAL'),
(1116, 15, 'ZETAQUIRA'),
(1117, 25, 'ZIPACON'),
(1118, 25, 'ZIPAQUIRA'),
(1119, 47, 'ZAPAYAN'),
(1120, 47, 'ZONA BANANERA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `id_departamento` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id_departamento`, `nombre`) VALUES
(5, 'ANTIOQUIA'),
(8, 'ATLANTICO'),
(11, 'BOGOTA'),
(13, 'BOLIVAR'),
(15, 'BOYACA'),
(17, 'CALDAS'),
(18, 'CAQUETA'),
(19, 'CAUCA'),
(20, 'CESAR'),
(23, 'CORDOBA'),
(25, 'CUNDINAMARCA'),
(27, 'CHOCO'),
(41, 'HUILA'),
(44, 'LA GUAJIRA'),
(47, 'MAGDALENA'),
(50, 'META'),
(52, 'NARINIO'),
(54, 'N. DE SANTANDER'),
(63, 'QUINDIO'),
(66, 'RISARALDA'),
(68, 'SANTANDER'),
(70, 'SUCRE'),
(73, 'TOLIMA'),
(76, 'VALLE DEL CAUCA'),
(81, 'ARAUCA'),
(85, 'CASANARE'),
(86, 'PUTUMAYO'),
(88, 'SAN ANDRES'),
(91, 'AMAZONAS'),
(94, 'GUAINIA'),
(95, 'GUAVIARE'),
(97, 'VAUPES'),
(99, 'VICHADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `establecimiento`
--

CREATE TABLE `establecimiento` (
  `id_establecimiento` int(11) NOT NULL,
  `id_representante` int(11) NOT NULL,
  `id_tipo_doc` int(11) NOT NULL,
  `num_doc` double DEFAULT NULL,
  `razon_social` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion_principal` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `establecimiento`
--

INSERT INTO `establecimiento` (`id_establecimiento`, `id_representante`, `id_tipo_doc`, `num_doc`, `razon_social`, `telefono`, `direccion_principal`, `fecha_registro`, `estado_registro`) VALUES
(1, 1, 5, 90098472, 'Tu Ensayadero', '3144352585', 'Cll 15 # 12 - 17 Chapinero', '2020-09-20 22:48:05', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_reserva`
--

CREATE TABLE `estado_reserva` (
  `id_estado_reserva` int(11) NOT NULL,
  `estado` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipo_alerta` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estado_reserva`
--

INSERT INTO `estado_reserva` (`id_estado_reserva`, `estado`, `descripcion`, `tipo_alerta`, `fecha_registro`, `estado_registro`) VALUES
(1, 'Abierta', 'Solicitud abierta por confirmar', 'alert alert-success', '2020-09-25 21:26:40', 'Activo'),
(2, 'Confirmada', 'Solicitud confirmada', 'alert alert-warning', '2020-09-25 21:26:40', 'Activo'),
(3, 'Cancelada', 'Solicitud cancelada', 'alert alert-danger', '2020-09-25 21:26:40', 'Activo'),
(4, 'Recargos', 'Solicitud con recargos adicionales', 'alert alert-info', '2020-09-25 21:26:40', 'Activo'),
(5, 'Finalizada', 'Solicitud finalizada', 'alert alert-secondary', '2020-09-25 21:26:40', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favorito`
--

CREATE TABLE `favorito` (
  `id_usuario` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gasto`
--

CREATE TABLE `gasto` (
  `id_gasto` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `id_tipo_gasto` int(11) NOT NULL,
  `monto` double DEFAULT NULL,
  `fecha_gasto` date DEFAULT NULL,
  `comentarios` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen`
--

CREATE TABLE `imagen` (
  `id_imagen` int(11) NOT NULL,
  `id_activo` int(11) DEFAULT NULL,
  `id_sala` int(11) DEFAULT NULL,
  `ruta` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineamiento_calificacion`
--

CREATE TABLE `lineamiento_calificacion` (
  `id_lineamiento_calificacion` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id_mantenimiento` int(11) NOT NULL,
  `id_movimiento` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `observaciones_ingreso` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_retorno` date DEFAULT NULL,
  `observaciones_retorno` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje_contacto`
--

CREATE TABLE `mensaje_contacto` (
  `id_mensaje_contacto` bigint(20) NOT NULL,
  `apellido` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `asunto` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado_registro` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `mensaje` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mensaje_contacto`
--

INSERT INTO `mensaje_contacto` (`id_mensaje_contacto`, `apellido`, `asunto`, `correo`, `estado_registro`, `fecha_registro`, `mensaje`, `nombre`) VALUES
(1, 'garzon', 'prueba', 'felipegarxon@hotmail.com', 'Activo', '2020-09-07 17:44:16', 'mensaje de contacto 07092020', 'felipe'),
(2, 'MORA', 'PRUEBA', 'JUANITA@CORREO.COM', 'Activo', '2020-09-07 19:10:23', 'MENSAJE 2', 'JUANITA'),
(3, 'MARTINEZ', 'Prueba ', 'hugo@correo.com', 'Activo', '2020-09-14 00:10:45', 'Mensaje de prueba 14092020', 'HUGO'),
(4, 'morales', 'prueba', 'martha@correo.com', 'Activo', '2020-09-14 18:41:55', 'Mensaje de prueba desde la landing page', 'martha'),
(5, 'MORALES', 'Prueba', 'jmorales@corre.com', 'Activo', '2020-09-16 16:24:12', '16 09 2020', 'JORGE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento_activo`
--

CREATE TABLE `movimiento_activo` (
  `id_movimiento` int(11) NOT NULL,
  `id_activo` int(11) NOT NULL,
  `id_sala` int(11) DEFAULT NULL,
  `tipo_movimiento` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'entrada-salida',
  `motivo` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'uso sala - bodega - mantenimiento',
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificacion`
--

CREATE TABLE `notificacion` (
  `id_notificacion` int(11) NOT NULL,
  `id_usuario_envia` int(11) NOT NULL,
  `id_usuario_recibe` int(11) NOT NULL,
  `tipo_notificacion` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mensaje` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `leida` tinyint(4) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `nombre`, `descripcion`) VALUES
(1, 'ADMINISTRADOR', 'ADMINISTRADOR DE LA SUCURSAL'),
(2, 'AUXILIAR', 'AUXILIAR DE BODEGA'),
(3, 'MUSICO', 'USUARIO MUSICO'),
(4, 'SUPERADMINISTRADOR', 'ADMINISTRACION DE VENTANAS DE MANTENIMIENTO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `codigo` int(11) NOT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaNacimiento` datetime DEFAULT NULL,
  `nombres` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `sexo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `id_tipo_doc` int(11) NOT NULL,
  `num_doc` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `razon_social` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor_contacto`
--

CREATE TABLE `proveedor_contacto` (
  `id_proveedor_contacto` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recargo`
--

CREATE TABLE `recargo` (
  `id_reservacion` int(11) NOT NULL,
  `id_tipo_recargo` int(11) NOT NULL,
  `monto` double DEFAULT NULL,
  `descripcion_caso` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `comentarios` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representante`
--

CREATE TABLE `representante` (
  `id_representante` int(11) NOT NULL,
  `id_tipo_doc` int(11) NOT NULL,
  `num_doc` double DEFAULT NULL,
  `nombres` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellidos` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `representante`
--

INSERT INTO `representante` (`id_representante`, `id_tipo_doc`, `num_doc`, `nombres`, `apellidos`, `correo`, `fecha_registro`, `estado_registro`) VALUES
(1, 1, 20925925, 'ARMANDO ESTEBAN', 'QUITO', 'armandobanquito@correo.com', '2020-09-20 22:45:10', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacion`
--

CREATE TABLE `reservacion` (
  `id_reservacion` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_sistema_hora` int(11) NOT NULL,
  `precio` double NOT NULL,
  `comentarios` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `id_estado_reserva` int(11) NOT NULL,
  `calificacion` int(11) DEFAULT NULL,
  `fecha_registro` datetime NOT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `reservacion`
--

INSERT INTO `reservacion` (`id_reservacion`, `id_sala`, `id_usuario`, `fecha`, `id_sistema_hora`, `precio`, `comentarios`, `id_estado_reserva`, `calificacion`, `fecha_registro`, `estado_registro`) VALUES
(1, 1, 8, '2020-09-01', 1, 45000, 'ninguno', 5, 2, '2020-09-25 21:28:46', 'Activo'),
(2, 2, 12, '2020-09-01', 1, 45000, 'ninguno', 5, 5, '2020-09-25 21:29:29', 'Activo'),
(3, 2, 12, '2020-09-02', 1, 45000, 'ninguno', 5, 1, '2020-09-25 21:30:23', 'Activo'),
(4, 1, 12, '2020-09-03', 1, 45000, 'ninguno', 5, 2, '2020-09-25 21:34:10', 'Activo'),
(5, 2, 8, '2020-09-03', 1, 45000, 'ninguno', 5, 4, '2020-09-25 21:34:10', 'Activo'),
(6, 3, 8, '2020-09-04', 1, 45000, 'ninguno', 5, 4, '2020-09-25 21:34:10', 'Activo'),
(7, 3, 12, '2020-09-05', 1, 45000, 'ninguno', 5, 4, '2020-09-25 21:34:10', 'Activo'),
(8, 1, 12, '2020-09-06', 1, 45000, 'ninguno', 5, 5, '2020-09-25 21:34:10', 'Activo'),
(9, 4, 8, '2020-09-07', 1, 45000, 'ninguno', 5, 3, '2020-09-25 21:34:10', 'Activo'),
(10, 4, 12, '2020-09-08', 1, 45000, 'ninguno', 5, 3, '2020-09-25 21:34:10', 'Activo'),
(11, 1, 12, '2020-09-09', 1, 45000, 'ninguno', 5, 3, '2020-09-25 21:34:10', 'Activo'),
(12, 1, 12, '2020-09-10', 1, 45000, 'ninguno', 5, 2, '2020-09-25 21:34:10', 'Activo'),
(13, 2, 8, '2020-09-11', 1, 45000, 'ninguno', 5, 1, '2020-09-25 21:34:10', 'Activo'),
(14, 3, 12, '2020-09-11', 1, 45000, 'ninguno', 5, 5, '2020-09-25 21:34:10', 'Activo'),
(15, 3, 12, '2020-09-12', 1, 45000, 'ninguno', 5, 3, '2020-09-25 21:34:10', 'Activo'),
(16, 3, 8, '2020-09-13', 1, 45000, 'ninguno', 5, 2, '2020-09-25 21:34:10', 'Activo'),
(17, 3, 12, '2020-09-16', 1, 45000, 'ninguno', 5, 2, '2020-09-25 21:34:10', 'Activo'),
(18, 4, 8, '2020-09-20', 1, 45000, 'ninguno', 5, 3, '2020-09-25 21:34:10', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacionestado`
--

CREATE TABLE `reservacionestado` (
  `id_reservacion` int(11) NOT NULL,
  `comentarios` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `id_estado_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacion_estado`
--

CREATE TABLE `reservacion_estado` (
  `id_reservacion` int(11) NOT NULL,
  `id_estado_reserva` int(11) NOT NULL,
  `comentarios` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `id_sala` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `descripcion` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`id_sala`, `id_sucursal`, `nombre`, `precio`, `descripcion`, `fecha_registro`, `estado_registro`) VALUES
(1, 1, 'SALA AMARILLA', 35000, 'Sala de ensayo para grupos entusiastas', '2020-09-20 13:11:24', 'Activo'),
(2, 1, 'SALA AZUL', 40000, 'Prueba de registro', '2020-09-20 13:44:40', 'Activo'),
(3, 1, 'SALA VERDE', 50000, 'Descripcion de la sala verde', '2020-09-20 14:43:27', 'Activo'),
(4, 1, 'SALA MORADA', 56000, 'DESCRIPCION DE SALA MORADA', '2020-09-21 19:22:47', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sistema_hora`
--

CREATE TABLE `sistema_hora` (
  `id_sistema_hora` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_finalizacion` time DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sistema_hora`
--

INSERT INTO `sistema_hora` (`id_sistema_hora`, `id_sucursal`, `hora_inicio`, `hora_finalizacion`, `fecha_registro`, `estado_registro`) VALUES
(1, 1, '10:00:00', '12:00:00', '2020-09-25 21:21:07', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `id_sucursal` int(11) NOT NULL,
  `id_establecimiento` int(11) NOT NULL,
  `id_ciudad` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`id_sucursal`, `id_establecimiento`, `id_ciudad`, `nombre`, `direccion`, `telefono`, `fecha_registro`, `estado_registro`) VALUES
(1, 1, 392, 'SUCURSAL TU ENSAYADERO', 'CLL 15 #21 - 2', '3144352585', '2020-09-20 11:03:18', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono`
--

CREATE TABLE `telefono` (
  `codigo` int(11) NOT NULL,
  `numero` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `codigoPersona` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipogasto`
--

CREATE TABLE `tipogasto` (
  `id_tipo_gasto` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiporecargo`
--

CREATE TABLE `tiporecargo` (
  `id_tipo_recargo` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado_registro` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_doc`
--

CREATE TABLE `tipo_doc` (
  `id_tipo_doc` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `abreviacion` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_doc`
--

INSERT INTO `tipo_doc` (`id_tipo_doc`, `nombre`, `abreviacion`) VALUES
(1, 'CÉDULA DE CIUDADANÍA', 'CC'),
(2, 'CÉDULA DE EXTRANJERÍA', 'CE'),
(3, 'PASAPORTE', 'PAS'),
(4, 'TARJETA DE IDENTIDAD', 'TI'),
(5, 'NIT', 'NIT');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_gasto`
--

CREATE TABLE `tipo_gasto` (
  `id_tipo_gasto` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipo` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_recargo`
--

CREATE TABLE `tipo_recargo` (
  `id_tipo_recargo` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_sucursal` int(11) DEFAULT NULL,
  `id_perfil` int(11) NOT NULL,
  `id_tipo_doc` int(11) NOT NULL,
  `num_doc` double NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `foto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `codigo_validacion` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `validacion_cuenta` tinyint(4) NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `estado_registro` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_sucursal`, `id_perfil`, `id_tipo_doc`, `num_doc`, `nombre`, `apellido`, `telefono`, `correo`, `clave`, `foto`, `codigo_validacion`, `validacion_cuenta`, `fecha_registro`, `estado_registro`) VALUES
(2, 1, 1, 1, 1069753422, 'MIGUEL FELIPE', 'PEÑUELA GARZON', '3144352585', 'admin@gmail.com', 'admin', NULL, 'CODE1234', 1, '2020-09-17 07:45:15', 'Activo'),
(3, 1, 2, 1, 2087472, 'CLAUDIA', 'LOPEZ', '311224322', 'auxiliary@gmail.com', 'auxiliary', NULL, 'CODE1234', 1, '2020-09-17 07:45:15', 'Activo'),
(8, NULL, 3, 1, 10697361, 'Miguel Felipe', 'Peñuela Garzon', '31432425', 'felipegarxon@hotmail.com', 'Felipe', NULL, 'yWQS7DFoPNAR73zejOtDcY9hp1JSRBuEquS6jaeQ1Sbi1dRahaXmA5RtQfW2HYgNfvmGjs8tsHlVehOb2yvfyp3sKbiX1ksTge56', 1, '2020-09-19 15:00:04', 'Activo'),
(12, NULL, 3, 1, 1069753, 'MIGUEL FELIPE', 'PEÑUELA GARZON', '31432425', 'mgarzonnaranjo@gmail.com', 'Felipegarzon2020', NULL, '8zhlE9S7bh8VCMmV9Ny80dKfndGjgbAoaFBc7Lq07iIGZZr1fmzgFQD4Kk5YtQjPmclq1ICh7Ni88FRhpibtt6ntVKnNcb3FTP1j', 1, '2020-09-21 19:18:17', 'Activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `activo`
--
ALTER TABLE `activo`
  ADD PRIMARY KEY (`id_activo`),
  ADD KEY `fk_activo_sucursal1_idx` (`id_sucursal`);

--
-- Indices de la tabla `calificacion`
--
ALTER TABLE `calificacion`
  ADD KEY `fk_calificacion_reservacion_reservacion1_idx` (`id_reservacion`),
  ADD KEY `fk_calificacion_reservacion_lineamiento_calificacion1_idx` (`id_lineamiento_calificacion`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id_ciudad`),
  ADD KEY `fk_ciudad_departamento1_idx` (`id_departamento`);

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id_departamento`);

--
-- Indices de la tabla `establecimiento`
--
ALTER TABLE `establecimiento`
  ADD PRIMARY KEY (`id_establecimiento`),
  ADD KEY `fk_establecimiento_tipo_doc_idx` (`id_tipo_doc`),
  ADD KEY `fk_establecimiento_representante1_idx` (`id_representante`);

--
-- Indices de la tabla `estado_reserva`
--
ALTER TABLE `estado_reserva`
  ADD PRIMARY KEY (`id_estado_reserva`);

--
-- Indices de la tabla `favorito`
--
ALTER TABLE `favorito`
  ADD PRIMARY KEY (`id_usuario`,`id_sala`),
  ADD KEY `fk_favorito_usuario1_idx` (`id_usuario`),
  ADD KEY `fk_favorito_sala1_idx` (`id_sala`);

--
-- Indices de la tabla `gasto`
--
ALTER TABLE `gasto`
  ADD PRIMARY KEY (`id_gasto`),
  ADD KEY `fk_gasto_sucursal1_idx` (`id_sucursal`),
  ADD KEY `fk_gasto_tipo_gasto1_idx` (`id_tipo_gasto`);

--
-- Indices de la tabla `imagen`
--
ALTER TABLE `imagen`
  ADD PRIMARY KEY (`id_imagen`),
  ADD KEY `fk_imagen_activo1_idx` (`id_activo`),
  ADD KEY `fk_imagen_sala1_idx` (`id_sala`);

--
-- Indices de la tabla `lineamiento_calificacion`
--
ALTER TABLE `lineamiento_calificacion`
  ADD PRIMARY KEY (`id_lineamiento_calificacion`);

--
-- Indices de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `fk_mantenimiento_movimiento1_idx` (`id_movimiento`),
  ADD KEY `fk_mantenimiento_proveedor1_idx` (`id_proveedor`);

--
-- Indices de la tabla `mensaje_contacto`
--
ALTER TABLE `mensaje_contacto`
  ADD PRIMARY KEY (`id_mensaje_contacto`);

--
-- Indices de la tabla `movimiento_activo`
--
ALTER TABLE `movimiento_activo`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD KEY `fk_movimiento_activo1_idx` (`id_activo`),
  ADD KEY `fk_movimiento_sala1_idx` (`id_sala`);

--
-- Indices de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD PRIMARY KEY (`id_notificacion`),
  ADD KEY `fk_notificacion_usuario1_idx` (`id_usuario_envia`),
  ADD KEY `fk_notificacion_usuario2_idx` (`id_usuario_recibe`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id_perfil`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`),
  ADD KEY `fk_proveedor_tipo_doc1_idx` (`id_tipo_doc`);

--
-- Indices de la tabla `proveedor_contacto`
--
ALTER TABLE `proveedor_contacto`
  ADD PRIMARY KEY (`id_proveedor_contacto`),
  ADD KEY `fk_proveedor_contacto_proveedor1_idx` (`id_proveedor`);

--
-- Indices de la tabla `recargo`
--
ALTER TABLE `recargo`
  ADD KEY `fk_recargo_reservacion1_idx` (`id_reservacion`),
  ADD KEY `fk_recargo_tipo_recargo1_idx` (`id_tipo_recargo`);

--
-- Indices de la tabla `representante`
--
ALTER TABLE `representante`
  ADD PRIMARY KEY (`id_representante`),
  ADD KEY `fk_representante_tipo_doc1_idx` (`id_tipo_doc`);

--
-- Indices de la tabla `reservacion`
--
ALTER TABLE `reservacion`
  ADD PRIMARY KEY (`id_reservacion`),
  ADD KEY `fk_reservacion_sala1_idx` (`id_sala`),
  ADD KEY `fk_reservacion_usuario1_idx` (`id_usuario`),
  ADD KEY `fk_reservacion_sistema_hora1_idx` (`id_sistema_hora`),
  ADD KEY `fk_reservacion_estado_reserva1_idx` (`id_estado_reserva`);

--
-- Indices de la tabla `reservacionestado`
--
ALTER TABLE `reservacionestado`
  ADD PRIMARY KEY (`id_reservacion`);

--
-- Indices de la tabla `reservacion_estado`
--
ALTER TABLE `reservacion_estado`
  ADD KEY `fk_reservacion_estado_reservacion1_idx` (`id_reservacion`),
  ADD KEY `fk_reservacion_estado_estado_reserva1_idx` (`id_estado_reserva`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id_sala`),
  ADD KEY `fk_sala_sucursal1_idx` (`id_sucursal`);

--
-- Indices de la tabla `sistema_hora`
--
ALTER TABLE `sistema_hora`
  ADD PRIMARY KEY (`id_sistema_hora`),
  ADD KEY `fk_sistema_hora_sucursal1_idx` (`id_sucursal`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`id_sucursal`),
  ADD KEY `fk_sucursal_establecimiento1_idx` (`id_establecimiento`),
  ADD KEY `id_ciudad` (`id_ciudad`);

--
-- Indices de la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_telefono_codigoPersona` (`codigoPersona`);

--
-- Indices de la tabla `tipogasto`
--
ALTER TABLE `tipogasto`
  ADD PRIMARY KEY (`id_tipo_gasto`);

--
-- Indices de la tabla `tiporecargo`
--
ALTER TABLE `tiporecargo`
  ADD PRIMARY KEY (`id_tipo_recargo`);

--
-- Indices de la tabla `tipo_doc`
--
ALTER TABLE `tipo_doc`
  ADD PRIMARY KEY (`id_tipo_doc`);

--
-- Indices de la tabla `tipo_gasto`
--
ALTER TABLE `tipo_gasto`
  ADD PRIMARY KEY (`id_tipo_gasto`);

--
-- Indices de la tabla `tipo_recargo`
--
ALTER TABLE `tipo_recargo`
  ADD PRIMARY KEY (`id_tipo_recargo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `fk_usuario_tipo_doc1_idx` (`id_tipo_doc`),
  ADD KEY `fk_usuario_perfil1_idx` (`id_perfil`),
  ADD KEY `id_sucursal` (`id_sucursal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `activo`
--
ALTER TABLE `activo`
  MODIFY `id_activo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id_ciudad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1121;

--
-- AUTO_INCREMENT de la tabla `establecimiento`
--
ALTER TABLE `establecimiento`
  MODIFY `id_establecimiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `gasto`
--
ALTER TABLE `gasto`
  MODIFY `id_gasto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `imagen`
--
ALTER TABLE `imagen`
  MODIFY `id_imagen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `lineamiento_calificacion`
--
ALTER TABLE `lineamiento_calificacion`
  MODIFY `id_lineamiento_calificacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `id_mantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje_contacto`
--
ALTER TABLE `mensaje_contacto`
  MODIFY `id_mensaje_contacto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `movimiento_activo`
--
ALTER TABLE `movimiento_activo`
  MODIFY `id_movimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  MODIFY `id_notificacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor_contacto`
--
ALTER TABLE `proveedor_contacto`
  MODIFY `id_proveedor_contacto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `representante`
--
ALTER TABLE `representante`
  MODIFY `id_representante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `reservacion`
--
ALTER TABLE `reservacion`
  MODIFY `id_reservacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `reservacionestado`
--
ALTER TABLE `reservacionestado`
  MODIFY `id_reservacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `id_sala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `sistema_hora`
--
ALTER TABLE `sistema_hora`
  MODIFY `id_sistema_hora` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `telefono`
--
ALTER TABLE `telefono`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipogasto`
--
ALTER TABLE `tipogasto`
  MODIFY `id_tipo_gasto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tiporecargo`
--
ALTER TABLE `tiporecargo`
  MODIFY `id_tipo_recargo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_recargo`
--
ALTER TABLE `tipo_recargo`
  MODIFY `id_tipo_recargo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `activo`
--
ALTER TABLE `activo`
  ADD CONSTRAINT `fk_activo_sucursal1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `calificacion`
--
ALTER TABLE `calificacion`
  ADD CONSTRAINT `fk_calificacion_reservacion_lineamiento_calificacion1` FOREIGN KEY (`id_lineamiento_calificacion`) REFERENCES `lineamiento_calificacion` (`id_lineamiento_calificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_calificacion_reservacion_reservacion1` FOREIGN KEY (`id_reservacion`) REFERENCES `reservacion` (`id_reservacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `fk_ciudad_departamento1` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `establecimiento`
--
ALTER TABLE `establecimiento`
  ADD CONSTRAINT `fk_establecimiento_representante1` FOREIGN KEY (`id_representante`) REFERENCES `representante` (`id_representante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_establecimiento_tipo_doc` FOREIGN KEY (`id_tipo_doc`) REFERENCES `tipo_doc` (`id_tipo_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `favorito`
--
ALTER TABLE `favorito`
  ADD CONSTRAINT `fk_favorito_sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_favorito_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `gasto`
--
ALTER TABLE `gasto`
  ADD CONSTRAINT `fk_gasto_sucursal1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_gasto_tipo_gasto1` FOREIGN KEY (`id_tipo_gasto`) REFERENCES `tipo_gasto` (`id_tipo_gasto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `imagen`
--
ALTER TABLE `imagen`
  ADD CONSTRAINT `fk_imagen_activo1` FOREIGN KEY (`id_activo`) REFERENCES `activo` (`id_activo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_imagen_sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD CONSTRAINT `fk_mantenimiento_movimiento1` FOREIGN KEY (`id_movimiento`) REFERENCES `movimiento_activo` (`id_movimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_mantenimiento_proveedor1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `movimiento_activo`
--
ALTER TABLE `movimiento_activo`
  ADD CONSTRAINT `fk_movimiento_activo1` FOREIGN KEY (`id_activo`) REFERENCES `activo` (`id_activo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_movimiento_sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD CONSTRAINT `fk_notificacion_usuario1` FOREIGN KEY (`id_usuario_envia`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_notificacion_usuario2` FOREIGN KEY (`id_usuario_recibe`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `fk_proveedor_tipo_doc1` FOREIGN KEY (`id_tipo_doc`) REFERENCES `tipo_doc` (`id_tipo_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `proveedor_contacto`
--
ALTER TABLE `proveedor_contacto`
  ADD CONSTRAINT `fk_proveedor_contacto_proveedor1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `recargo`
--
ALTER TABLE `recargo`
  ADD CONSTRAINT `fk_recargo_reservacion1` FOREIGN KEY (`id_reservacion`) REFERENCES `reservacion` (`id_reservacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_recargo_tipo_recargo1` FOREIGN KEY (`id_tipo_recargo`) REFERENCES `tipo_recargo` (`id_tipo_recargo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `representante`
--
ALTER TABLE `representante`
  ADD CONSTRAINT `fk_representante_tipo_doc1` FOREIGN KEY (`id_tipo_doc`) REFERENCES `tipo_doc` (`id_tipo_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reservacion`
--
ALTER TABLE `reservacion`
  ADD CONSTRAINT `fk_reservacion_estado_reserva1` FOREIGN KEY (`id_estado_reserva`) REFERENCES `estado_reserva` (`id_estado_reserva`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservacion_sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservacion_sistema_hora1` FOREIGN KEY (`id_sistema_hora`) REFERENCES `sistema_hora` (`id_sistema_hora`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservacion_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reservacion_estado`
--
ALTER TABLE `reservacion_estado`
  ADD CONSTRAINT `fk_reservacion_estado_estado_reserva1` FOREIGN KEY (`id_estado_reserva`) REFERENCES `estado_reserva` (`id_estado_reserva`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservacion_estado_reservacion1` FOREIGN KEY (`id_reservacion`) REFERENCES `reservacion` (`id_reservacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `fk_sala_sucursal1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sistema_hora`
--
ALTER TABLE `sistema_hora`
  ADD CONSTRAINT `fk_sistema_hora_sucursal1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `fk_sucursal_establecimiento1` FOREIGN KEY (`id_establecimiento`) REFERENCES `establecimiento` (`id_establecimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sucursal_ibfk_1` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id_ciudad`);

--
-- Filtros para la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD CONSTRAINT `FK_telefono_codigoPersona` FOREIGN KEY (`codigoPersona`) REFERENCES `persona` (`codigo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_perfil1` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_tipo_doc1` FOREIGN KEY (`id_tipo_doc`) REFERENCES `tipo_doc` (`id_tipo_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
