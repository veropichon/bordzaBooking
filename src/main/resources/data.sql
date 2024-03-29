
-- Discipline --
INSERT INTO skateschool.discipline (dis_id, dis_label) VALUES (1, 'Skateboard');
INSERT INTO skateschool.discipline (dis_id, dis_label) VALUES (2, 'Longboard descente');
INSERT INTO skateschool.discipline (dis_id, dis_label) VALUES (3, 'Longboard dancing');

-- Level --
INSERT INTO skateschool.level (lev_id, lev_client_label, lev_course_label) VALUES (1, 'Débutant', 'Débutant');
INSERT INTO skateschool.level (lev_id, lev_client_label, lev_course_label) VALUES (2, 'Confirmé', 'Perfectionnement');

-- Location --
INSERT INTO skateschool.location (loc_id, loc_deleted, loc_label) VALUES (1, false, 'Rocher Palmer');
INSERT INTO skateschool.location (loc_id, loc_deleted, loc_label) VALUES (2, false, 'Skatepark Colbert');
INSERT INTO skateschool.location (loc_id, loc_deleted, loc_label) VALUES (3, false, 'Darwin');

-- Duration --
INSERT INTO skateschool.duration (dur_id, dur_label) VALUES (1, '1 heure');
INSERT INTO skateschool.duration (dur_id, dur_label) VALUES (2, '2 heures');
INSERT INTO skateschool.duration (dur_id, dur_label) VALUES (3, '3 heures');

-- User --
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role, usr_validated) VALUES (1, 'vpichon@gmail.com', '$2a$10$DIsPpEm0fNGLdLdX3OJKIe37YixGn.9Cf5JL8a4avwefeH.FpZ4Ve', 'CLIENT',true);
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role, usr_validated) VALUES (2, 'titi@test.fr', '$2a$10$DIsPpEm0fNGLdLdX3OJKIe37YixGn.9Cf5JL8a4avwefeH.FpZ4Ve', 'CLIENT',true);
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role, usr_validated) VALUES (3, 'mariehelene.delteil@orange.fr', '$2a$10$DIsPpEm0fNGLdLdX3OJKIe37YixGn.9Cf5JL8a4avwefeH.FpZ4Ve', 'CLIENT',true);
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role, usr_validated) VALUES (4, 'valerian24@hotmail.fr', '$2a$10$y3AQCrwJbTpvckuxyAPntuVzr0Xj3uWcfq3Qylbgk1Ivhn8wBeopS', 'ADMIN',true);

-- Client --
INSERT INTO skateschool.client (cli_id, cli_birthdate, cli_city, cli_comment, cli_deleted, cli_firstname, cli_height, cli_lastname, cli_phone, cli_tutor_email, cli_tutor_firstname, cli_tutor_lastname, cli_tutor_phone, cli_weight, cli_zipcode, level_lev_id, user_usr_id) VALUES (1, '1965-11-08', 'Martillac', 'Pizza', false, 'Véronique', null, 'Pichon', '0643267875', null, null, null, null, null, '33650', 1, 1);
INSERT INTO skateschool.client (cli_id, cli_birthdate, cli_city, cli_comment, cli_deleted, cli_firstname, cli_height, cli_lastname, cli_phone, cli_tutor_email, cli_tutor_firstname, cli_tutor_lastname, cli_tutor_phone, cli_weight, cli_zipcode, level_lev_id, user_usr_id) VALUES (2, '2009-06-03', 'Pessac', null, false, 'Laura', null, 'Tropas', '0623457689', 'ctropa@gmail.com', 'Christelle', 'Tropa', '0678543209', null, '33600', 1, 2);
INSERT INTO skateschool.client (cli_id, cli_birthdate, cli_city, cli_comment, cli_deleted, cli_firstname, cli_height, cli_lastname, cli_phone, cli_tutor_email, cli_tutor_firstname, cli_tutor_lastname, cli_tutor_phone, cli_weight, cli_zipcode, level_lev_id, user_usr_id) VALUES (3, '1999-11-18', 'Cenon', null, false, 'Marie-Hélène', null, 'Delteil', '0634875632', null, null, null, null, null, '33150', 1, 3);

-- Course --
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_creator_id, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (1, '', false, 'Super cours de Skate pour débutant!', '2019-09-24 15:00:00', 1, true, 'Skateboard', '2019-09-24 16:00:00', false, false, 1, 1, 2, 1);
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_creator_id, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (2, '', false, 'Super cours de LongBoard dans les coteaux pour débutant!', '2019-09-26 11:00:00', 1, true, 'Longboard Descente', '2019-09-26 13:00:00', false, false, 2, 1, 1, 2);
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_creator_id, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (3, '', false, 'Super cours de LongBoard dancing! Bouge ton corps!!', '2019-09-27 17:00:00', 1, true, 'Longboard Dancing', '2019-09-27 18:00:00', false, false, 3, 2, 3, 1);
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_creator_id, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (4, '', false, 'Un cours surprise rien que pour toi!', '2019-09-26 16:00:00', 1, true, 'Longboard', '2019-09-26 18:00:00', true, true, 3, 1, 2, 2);

-- Booking --
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (1, false, false, true, 1, 2);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (2, true, false, true, 2, 1);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (3, false, false, true, 3, 1);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (4, false, true, true, 1, 4);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (5, false, false, true, 3, 3);
