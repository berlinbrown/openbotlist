--
-- Berlin Brown
-- 11/28/2007
-- patch_link_analysis.sql
--

--
-- Update the entity links table
-- to handle web analytical data
--
-- meta_descr_wct - Distinct words in description 
-- meta_keywords_wct - Distinct words in keywords
-- geo_locations_found - Listing of popular locations (cities, states, etc)

alter table entity_links ADD COLUMN generated_obj_id varchar(60) UNIQUE;

alter table entity_links ADD COLUMN user_up_votes int(11) DEFAULT 0;
alter table entity_links ADD COLUMN user_down_votes int(11) DEFAULT 0;

alter table entity_links ADD COLUMN links_ct int(11) DEFAULT 0;
alter table entity_links ADD COLUMN inbound_link_ct int(11) DEFAULT 0;
alter table entity_links ADD COLUMN outbound_links_ct int(11) DEFAULT 0;
alter table entity_links ADD COLUMN image_ct int(11) DEFAULT 0;

alter table entity_links ADD COLUMN meta_descr_len int(11) DEFAULT 0;
alter table entity_links ADD COLUMN meta_keywords_len int(11) DEFAULT 0;

alter table entity_links ADD COLUMN meta_descr_wct int(11) DEFAULT 0;
alter table entity_links ADD COLUMN meta_keywords_wct int(11) DEFAULT 0;
 
alter table entity_links ADD COLUMN geo_locations_ct int(11) DEFAULT 0;
alter table entity_links ADD COLUMN geo_locations_found varchar(128);
alter table entity_links ADD COLUMN document_size int(11) DEFAULT 0;
alter table entity_links ADD COLUMN request_time int(11) DEFAULT 0;

-- The object id status ensures that the object id value is static
alter table entity_links ADD COLUMN object_id_status TINYINT DEFAULT 0;

-- Add another web data collection item; number of 'p' tags
alter table entity_links ADD COLUMN para_tag_ct int(11) DEFAULT 0;

---
--- Additional keywords to link as a type.

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'CentralAfrica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'EasternAfrica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'IndianOcean', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'NorthernAfrica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'SouthernAfrica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'WesternAfrica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Americas', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', Central America', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'North America', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'South America', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'West Indies', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Antarctica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Atlantic Ocean', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Asia', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Europe', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Oceania', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Pacific', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Austria', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'CzechRepublic', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Hungary', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Liechtenstein', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Slovakia', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Switzerland', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Belgium', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'France', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Germany', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Ireland', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Luxembourg', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Monaco', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Netherlands', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'UnitedKingdom', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Canada', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Greenland', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'UnitedStates', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'zoeyzane', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'wilkenfeld', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'logcabin', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'republicans', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'emily', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'sander', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'republican', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'debate', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'youtube', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'debate', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'campania', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'restaurant', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'steve', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'winwood', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'crossroads', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'guitar', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'festival', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'blackkkk', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'kkk'

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'republican', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'candidates', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'kingdomheaven', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'aliettevazquez', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'allegorical', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'tiki', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'barber', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'keithkerr', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'muniz', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'campania', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'newjersey', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'julietaylor', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'wilson', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'fairtax', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'ericclapton', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'clapton', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'zoeyzane.com', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'brushfire', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'dilley', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'sextuplets', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'whitlock', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'huckabee', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'cnnyoutube', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'rayful', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'edmond', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'myyearbook.com', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'waterboarding', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'andersoncooper', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'mittromney', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Arts', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Animation', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Architecture', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'ArtHistory', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Business', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Accounting', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Aerospace', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Defense', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Agriculture', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Forestry', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Computers', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Algorithms', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Artificial', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'ArtificialLife', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Games', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Addictions', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Aging', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Alternative', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'ApartmentLiving', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'ConsumerInformation', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Antiques', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Audio', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Reference', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Almanacs', NOW());


insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Archives', NOW());


insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Africa', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Asia', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Caribbean', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Science', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Agriculture', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Anomalies', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Alternative', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Science', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Astronomy', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Shopping', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Antiques', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Collectibles', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Activism', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Advice', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'World', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Cesky', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Afrikaans', NOW());


insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'Arabic


insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'glycine 

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('phosphatidylcholine

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'myspace', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'petardas', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'hotmail.com', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'bbcweather', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'meraba', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'pantat', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'hallimash', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'youtube', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'ronpaul', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'paulron', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'ronnie', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'paull', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'noelia', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'britneyspears', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'myspace', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'google', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'manon', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'yahoo', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'huckabee', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'devteach', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Engadget', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Gizmodo', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Techcrunch', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Boing', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Huffington', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Lifehacker', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Ars', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Technica', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'WordPress', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Mashable', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Beppe', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Grillo', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'icanhascheezburger', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'DailyKos', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'TMZ', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'PerezHilton', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'PostSecret', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'Rowse', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('tech', 'TreeHugger', NOW());
  
-- End of Script
