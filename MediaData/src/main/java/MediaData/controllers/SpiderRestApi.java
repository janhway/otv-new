package MediaData.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import MediaData.dao.ProgramDao;
import MediaData.entity.Episode;
import MediaData.entity.Movie;
import MediaData.entity.Program;
import MediaData.msg.OttMedia;
import MediaData.msg.SubStoryInfo;

@Controller
@RequestMapping("/spiderRestApi")
public class SpiderRestApi {

	private final Logger log = LoggerFactory.getLogger(SpiderRestApi.class);

	@Autowired
	private ProgramDao programDao;

	@RequestMapping(value = "/ottmedia", method = RequestMethod.POST, headers = "Content-Type=application/json;charset=utf-8")
	public @ResponseBody
	String crtOttMedia(@RequestBody OttMedia om) {

		Program p = programDao.getProgram(om.getTitle());
		if (p == null) {
			p = new Program();
			p.setTitle(om.getTitle());
			p.setActors(om.getActors());
			p.setDirectors(om.getDirectors());
			p.setOriginCountry(om.getOriginCountry());
			p.setReleaseYear(om.getReleaseYear());
			p.setGenre(om.getGenre());
			p.setDescription(om.getDescription());
			p.setPicUrl(om.getPicUrl());
			List<Episode> epList = new ArrayList<Episode>();
			for (SubStoryInfo ssi : om.getEpisodeList()) {
				Episode ep = new Episode();
				Movie mv = new Movie();
				mv.setEpisode(ep);
				mv.setPlayUrl(ssi.getPlayUrl());
				Set<Movie> mvList = new HashSet<Movie>();
				mvList.add(mv);
				ep.setMovieList(mvList);
				ep.setProgram(p);
				ep.setTitle(ssi.getTitle());
				ep.setDuration(Integer.valueOf(ssi.getDuration()));
				ep.setPicUrl(ssi.getPicUrl());
				ep.setDescription(ssi.getDescrip());
				epList.add(ep);
			}
			p.setEpisodeList(epList);
			programDao.saveProgram(p);
		} else {
			log.info("add it later.");
		}

		return "ok";
	}
}
