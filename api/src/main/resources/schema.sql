DROP TABLE IF EXISTS team_project;
DROP TABLE IF EXISTS iteration_user_story;
DROP TABLE IF EXISTS team_member;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS iteration;
DROP TABLE IF EXISTS defect_feature;
DROP TABLE IF EXISTS defect_user_story;
DROP TABLE IF EXISTS defect;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS user_story;
DROP TABLE IF EXISTS feature;
DROP TABLE IF EXISTS feature_status;
DROP TABLE IF EXISTS "release";
DROP TABLE IF EXISTS project;

CREATE TABLE project (
	id UUID PRIMARY KEY NOT NULL,
	"name" TEXT NOT NULL,
	description TEXT
);

CREATE TABLE release (
	id UUID PRIMARY KEY,
	project_id UUID NOT NULL,
	version VARCHAR(16) NOT NULL,
	FOREIGN KEY(project_id) REFERENCES project(id)
);

CREATE TABLE feature_status (
    id UUID PRIMARY KEY,
    description TEXT NOT NULL,
    CONSTRAINT feature_status_desc_uq UNIQUE (description)
);

CREATE TABLE feature (
	id UUID PRIMARY KEY,
	code CHAR(8) NOT NULL,
	release_id UUID NOT NULL,
	"name" TEXT NOT NULL,
	description TEXT,
	estimation INTEGER,
	pct_completed SMALLINT NOT NULL DEFAULT 0,
	feature_status_id UUID NOT NULL,
	FOREIGN KEY(release_id) REFERENCES release(id),
	FOREIGN KEY(feature_status_id) REFERENCES feature_status(id),
	CONSTRAINT feature_code_uq UNIQUE (code),
	CONSTRAINT feature_pct_completed_ck CHECK (pct_completed >= 0 AND pct_completed <= 100)
);

CREATE TABLE user_story (
	id UUID PRIMARY KEY,
	project_id UUID NOT NULL,
	feature_id UUID NOT NULL,
	"name" TEXT NOT NULL,
	description TEXT,
	depends_on_id UUID,
	FOREIGN KEY(project_id) REFERENCES project(id),
	FOREIGN KEY(feature_id) REFERENCES feature(id),
	FOREIGN KEY(depends_on_id) REFERENCES user_story(id)
);

CREATE TABLE task (
	id UUID PRIMARY KEY,
	user_story_id UUID NOT NULL,
	"name" TEXT NOT NULL,
	description TEXT,
	FOREIGN KEY(user_story_id) REFERENCES user_story(id)
);

CREATE TABLE defect (
	id UUID PRIMARY KEY,
	project_id UUID NOT NULL,
	description TEXT,
	FOREIGN KEY(project_id) REFERENCES project(id)
);

CREATE TABLE defect_user_story (
	defect_id UUID ,
	user_story_id UUID,
	PRIMARY KEY(defect_id, user_story_id),
	FOREIGN KEY(defect_id) REFERENCES defect(id),
	FOREIGN KEY(user_story_id) REFERENCES user_story(id)
);

CREATE TABLE defect_feature (
	defect_id UUID,
	feature_id UUID,
	PRIMARY KEY(defect_id, feature_id),
	FOREIGN KEY(defect_id) REFERENCES defect(id),
	FOREIGN KEY(feature_id) REFERENCES feature(id)
);

CREATE TABLE iteration (
	id UUID PRIMARY KEY,
	project_id UUID NOT NULL,
	start_date TEXT NOT NULL,
	end_date TEXT NOT NULL,
	FOREIGN KEY(project_id) REFERENCES project(id)
);

CREATE TABLE "user" (
	id UUID PRIMARY KEY,
	first_name TEXT NOT NULL,
	last_name TEXT
);

CREATE TABLE team (
	id UUID PRIMARY KEY,
	"name" TEXT NOT NULL
);

CREATE TABLE team_member (
	user_id UUID,
	team_id UUID,
	PRIMARY KEY(user_id, team_id),
	FOREIGN KEY(user_id) REFERENCES "user"(id),
	FOREIGN KEY(team_id) REFERENCES team(id)
);

CREATE TABLE iteration_user_story (
	user_story_id UUID,
	team_id UUID,
	iteration_id UUID,
	PRIMARY KEY(user_story_id, team_id, iteration_id),
	FOREIGN KEY(user_story_id) REFERENCES user_story(id),
	FOREIGN KEY(team_id) REFERENCES team(id),
	FOREIGN KEY(iteration_id) REFERENCES iteration(id)
);

CREATE TABLE team_project (
	team_id UUID,
	project_id UUID,
	PRIMARY KEY(team_id, project_id),
	FOREIGN KEY(team_id) REFERENCES team(id),
	FOREIGN KEY(project_id) REFERENCES project(id)
);