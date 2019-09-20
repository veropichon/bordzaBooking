
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
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role) VALUES (1, 'toto@test.fr', 'eeeeeeeee', 'CLIENT');
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role) VALUES (2, 'titi@test.fr', 'RRRRRRR', 'CLIENT');
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role) VALUES (3, 'gilleshette@gmail.com', '4FFGYYYG', 'CLIENT');
INSERT INTO skateschool.user (usr_id, usr_email, usr_pwd, role) VALUES (4, 'mariehelene.delteil@gmail.com', 'adminadmin', 'ADMIN');

-- Client --
INSERT INTO skateschool.client (cli_id, cli_birthdate, cli_city, cli_comment, cli_deleted, cli_firstname, cli_height, cli_lastname, cli_phone, cli_tutor_email, cli_tutor_firstname, cli_tutor_lastname, cli_tutor_phone, cli_validated, cli_weight, cli_zipcode, level_lev_id, user_usr_id) VALUES (1, '1994-09-15', 'Bordeaux', 'Pizza', false, 'Paul', null, 'Smith', '0643267875', null, null, null, null, true, null, '33000', 2, 1);
INSERT INTO skateschool.client (cli_id, cli_birthdate, cli_city, cli_comment, cli_deleted, cli_firstname, cli_height, cli_lastname, cli_phone, cli_tutor_email, cli_tutor_firstname, cli_tutor_lastname, cli_tutor_phone, cli_validated, cli_weight, cli_zipcode, level_lev_id, user_usr_id) VALUES (2, '2009-06-03', 'Pessac', null, false, 'Laura', null, 'Tropas', '0623457689', 'ctropa@gmail.com', 'Christelle', 'Tropa', '0678543209', true, null, '33600', 1, 2);
INSERT INTO skateschool.client (cli_id, cli_birthdate, cli_city, cli_comment, cli_deleted, cli_firstname, cli_height, cli_lastname, cli_phone, cli_tutor_email, cli_tutor_firstname, cli_tutor_lastname, cli_tutor_phone, cli_validated, cli_weight, cli_zipcode, level_lev_id, user_usr_id) VALUES (3, '1999-11-18', 'Cenon', null, false, 'Gilles', null, 'Hette', '0634875632', null, null, null, null, true, null, '33150', 1, 3);

-- Course --
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (1, '', false, 'Super cours de Skate pour débutant!', '2019-09-16 15:00:00', true, 'Skateboard Skatepark Colbert', '2019-09-16 16:00:00', false, false, 1, 1, 2, 1);
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (2, '', false, 'Super cours de LongBoard dans les coteaux pour débutant!', '2019-09-17 11:00:00', true, 'Longboard Descente Rocher Palmer', '2019-09-17 13:00:00', false, false, 2, 1, 1, 2);
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (3, '', false, 'Super cours de LongBoard dancing! Bouge ton corps!!', '2019-09-18 17:00:00', true, 'Longboard Dancing Darwin', '2019-09-18 18:00:00', false, false, 3, 2, 3, 1);
INSERT INTO skateschool.course (crs_id, crs_comment, crs_deleted, crs_desc, crs_from_date, crs_published, crs_title, crs_to_date, crs_unavailable, crs_vip, discipline_dis_id, level_lev_id, location_loc_id, duration_dur_id) VALUES (4, '', false, 'Un cours surprise rien que pour toi!', '2019-09-19 16:00:00', true, 'Longboard VIP Skatepark Colbert', '2019-09-19 18:00:00', false, true, 3, 2, 2, 2);

-- Booking --
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (1, false, false, false, 1, 2);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (2, true, false, false, 2, 1);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (3, false, false, false, 3, 1);
INSERT INTO skateschool.booking (bk_id, bk_mat, bk_vip, bk_validated, client_cli_id, course_crs_id) VALUES (4, false, true, false, 1, 4);