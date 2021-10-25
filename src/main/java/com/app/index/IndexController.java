package com.app.index;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//API LAYER
@RestController //make this class to serve as Rest endpoints
@RequestMapping(path = "api/v1/index")
public class IndexController {

     //Spring Bean.
    private final IndexService indexService;

    //IndexService is autowired for Rest. 
    //IndexService is auto-instantiated/injected into the constructor.
    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;

        //Below statement to be avoided, insteand use dependency injection.
        //this.indexService = new indexService();
    }

    //Rest endpoint: Get data from the server.
    @GetMapping
	public List<Index> getAllIndices() {
        return indexService.getAllIndices();
	}

    //Rest endpoint: Get data from the server.
    @GetMapping(path = "{indexId}")
	public Optional<Index> getSingleIndex(@PathVariable("indexId") Long indexId) {
        return indexService.getSingleIndex(indexId);
	}

    //Rest endpoint: Post data to the server.
    //Take response body and map it into a student object.
    @PostMapping
    public void addNewIndex(@RequestBody Index index) {    
        indexService.addNewIndex(index);
    }

    //Rest endpoint: Delete data from the server.
    @DeleteMapping(path = "{indexId}")
    public void deleteIndex(@PathVariable("indexId") Long indexId) {
        indexService.deleteIndex(indexId);
    }
    
    //Rest endpoint: Update data from the server.
    @PutMapping(path = "{indexId}") 
    public void updateIndex(
        @PathVariable("indexId") Long indexId,
        @RequestParam(required = false) String ticker,
        @RequestParam(required = false) String description) {

            indexService.updateIndex(indexId, ticker, description);
    }

}
