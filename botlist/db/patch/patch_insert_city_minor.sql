-- Berlin Brown
--
-- 8/20/2007

------
-- City oriented changes (add city category, MINOR)
------

alter table city_listing ADD COLUMN city_category varchar(10) default 'MINOR';

alter table city_listing ADD COLUMN state_abbr varchar(10);

--
-- Insert minor cities
--

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Birmingham, Al', 'MINOR', 'AL', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Tuscaloosa, Al', 'MINOR', 'AL', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Arlington, Tx', 'MINOR', 'TX', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('San Antonio, Tx', 'MINOR', 'TX', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Biloxi, Ms', 'MINOR', 'MS', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('New Orleans, La', 'MINOR', 'LA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Portland, Or', 'MINOR', 'OR', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Athens, Ga', 'MINOR', 'GA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Cleveland, Oh', 'MINOR', 'OH', NOW());


---
--- Second set

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Los Angeles, Ca', 'MAJOR', NULL, NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Wash DC', 'MAJOR', NULL, NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Sacremento, Ca', 'MINOR', 'CA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Denver, Co', 'MINOR', 'CO', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Jacksonville, Fl', 'MINOR', 'FL', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Orlando, Fl', 'MINOR', 'FL', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Hawaii', 'MINOR', 'HAWAII', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Indianapolis, IN', 'MINOR', 'IN', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Maine - Minor', 'MINOR', 'MAINE', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Massachusetts', 'MINOR', 'MASS', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('New Jersey', 'MINOR', 'NJ', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Paris, France', 'MINOR', 'FRANCE', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Alberta, Canada', 'MINOR', 'CANADA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Beijing, China', 'MINOR', 'CHINA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Hong Kong, China', 'MINOR', 'CHINA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Shanghai, China', 'MINOR', 'CHINA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Madrid, Spain', 'MINOR', 'SPAIN', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Mumbai, India', 'MINOR', 'INDIA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Delhi, India', 'MINOR', 'INDIA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Australia', 'MINOR', 'AUSTRALIA', NOW());

---
--- Add new reviews section

INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('c8ec2847bdac35595ffba82aa0f65fcbreviews', 'Reviews', NOW());

INSERT INTO post_sections(generated_id, section_name, created_on) 
			VALUES('e6ba9e9ab139f9c0b090cf77beaccNews', 'Local News', NOW());

-- End of the script

