package com.app.index;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//SERVICE LAYER (business logic to manage students).
@Service
public class IndexService {

    private final IndexRepository indexRepository;

    @Autowired //IndexRepository is autowired for Rest. indexRepository is auto-instantiated/injected into the constructor.
    public IndexService(IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
        //this.indexRepository = new indexRepository(); //to be avoided, insteand use dependency injection.
    } 

    public List<Index> getAllIndices() {
        return indexRepository.findAll();
    }

    public Optional<Index> getSingleIndex(Long indexId) {
        return indexRepository.findById(indexId);
    }

    public void addNewIndex(Index index) {
        Optional<Index> indexOptional = indexRepository.
            findIndexByTicker(index.getTicker());
        if (indexOptional.isPresent()) {
            throw new IllegalStateException("Ticker already in use.");
        }
        indexRepository.save(index);
    }

    public void deleteIndex(Long indexId) {
        boolean exists = indexRepository.existsById(indexId);
        if (!exists) {
            throw new IllegalStateException(
                "Index with id " + indexId + " does not exists.");
        }
        indexRepository.deleteById(indexId);
    }

    //Transactional annotation allows the entity (Index table/object) to go into manage state.
    //It's to avoid query the DB in JPSQL and instead use directly class methods such as getters/setters.
    @Transactional
    public void updateIndex(Long indexId, String ticker, String description) {
        Index index = indexRepository.findById(indexId)
            .orElseThrow(() -> new IllegalStateException(
                "Index with id " + indexId + " does not exists."));
            
        if (description != null && 
        description.length() > 0 && 
                !Objects.equals(index.getDescription(), index)) {
            index.setDescription(description);
        }

        if (ticker != null && 
        ticker.length() > 0 && 
                !Objects.equals(index.getTicker(), ticker)) {
            Optional<Index> indexOptional = indexRepository
                    .findIndexByTicker(ticker);
            if (indexOptional.isPresent()) {
                throw new IllegalStateException("Ticker is taken.");
            }
            index.setTicker(ticker);
        }
    }

}
