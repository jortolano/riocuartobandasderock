package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import spark.Request;
import spark.Response;

public class AlbumController {
    protected static AlbumController unique_instance = null;
    protected AlbumDAO dao;

    public AlbumController(AlbumDaoImpl albumDaoImpl) {
    	dao = albumDaoImpl;
	}

	public static AlbumController getInstance() {
        if (unique_instance == null)
            unique_instance = new AlbumController(new AlbumDaoImpl());
        return unique_instance;
    }

    public String create(Request req, Response res) {
    	if (req.queryParams("title") == null){
    		res.status(400);
    		res.body("Album title can't be null");
    		return res.body();
    	}
    	if (req.queryParams("title") == null && req.queryParams("release_date") == null){
    		//If both parameters are non existent, return false and a bad request.
    		res.status(400);
    		res.body("Both params can't be null");
    		return res.body();
    	}
    	DateFormat df = DateFormat.getDateInstance();
		try {
			dao.openCurrentSession();
			//Date should be in the next pattern: dd/mm/yyyy
			Date release_date = df.parse(req.queryParams("release_date"));
			boolean result = dao.createAlbum(req.params("title"), release_date);
			dao.closeCurrentSession();
	    	int http_status = result ? 201 : 409; 
	    	res.status(http_status);
	    	if (!result) res.body("Duplicate album"); //If the result of the creation was false, it means that there is a duplicate
    		return res.body();
		} catch (ParseException | IllegalArgumentException e) {
			//If an exception was thrown, then there was a problem with the parameters.
			e.printStackTrace();
			res.status(400);
			res.body("Bad parameters.");
    		return res.body();
		} catch (Exception e){
			e.printStackTrace();
			res.status(500);
			res.body("Internal server error");
    		return res.body();
		}
    	
    }

}
