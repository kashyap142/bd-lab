athletes = LOAD 'olympic_athletes.csv' USING PigStorage(',') AS (athlete_url: chararray,
athlete_full_name: chararray, games_participations: int, first_game: chararray, athlete_year_birth:
float, athlete_medals: chararray, bio: chararray);

hosts = LOAD 'olympic_hosts.csv' USING PigStorage(',') AS (game_slug: chararray,
game_end_date: chararray, game_start_date: chararray, game_location: chararray, game_name:
chararray, game_season: chararray, game_year: int);

DESCRIBE athletes;
DESCRIBE hosts;


tokyo_2020_athletes = FILTER athletes BY first_game == 'Tokyo 2020';
DUMP tokyo_2020_athletes;


games_in_china = FILTER hosts BY game_location == 'China';
DUMP games_in_china;


grouped_by_season = GROUP hosts BY game_season;
counted_by_season = FOREACH grouped_by_season GENERATE group AS game_season,
COUNT(hosts) AS num_games;
DUMP counted_by_season;


games_after_2000 = FILTER hosts BY game_year > 2000;
DUMP games_after_2000;