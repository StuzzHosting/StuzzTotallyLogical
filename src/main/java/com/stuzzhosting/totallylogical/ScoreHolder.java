package com.stuzzhosting.totallylogical;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ScoreHolder implements Serializable {
	private static final long serialVersionUID = 1L;

	ScoreHolder addScore( int score ) {
		TotalScore += score;
		MaxScore = Math.max( score, MaxScore );
		Deaths++;

		return this;
	}

	@Id
	@NotEmpty
	@NotNull
	String Player;

	@NotNull
	Long TotalScore = 0L;

	@NotNull
	Integer MaxScore = 0;

	@NotNull
	Integer Deaths = 0;

	// The remainder of this class is stupid and ugly and I hate it.
	public String getPlayer() {
		return Player;
	}

	public void setPlayer( String Player ) {
		this.Player = Player;
	}

	public Long getTotalScore() {
		return TotalScore;
	}

	public void setTotalScore( Long TotalScore ) {
		this.TotalScore = TotalScore;
	}

	public Integer getMaxScore() {
		return MaxScore;
	}

	public void setMaxScore( Integer MaxScore ) {
		this.MaxScore = MaxScore;
	}

	public Integer getDeaths() {
		return Deaths;
	}

	public void setDeaths( Integer Deaths ) {
		this.Deaths = Deaths;
	}

}