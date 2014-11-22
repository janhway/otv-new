package MediaData.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name="MOVIE")
public class Movie implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String playUrl;
	
	private Episode episode;

	@Id  
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO) 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "PLAY_URL")
	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}	
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="EPISODE_ID", referencedColumnName="ID")//外键为sut_id，与student中的id关联 
	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Movie)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		Movie other = (Movie) obj;

		return (this.id == other.id) && (this.playUrl == other.playUrl);
	}

	@Override
	public int hashCode() {
		return Long.toString(id).hashCode() * 37 + playUrl.hashCode();
	}
}
