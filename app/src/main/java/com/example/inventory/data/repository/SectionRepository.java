package com.example.inventory.data.repository;

import com.example.inventory.data.dao.SectionDAO;
import com.example.inventory.data.database.InventoryDatabase;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.section.SectionListContract;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SectionRepository implements SectionListContract.Repository {

    private SectionDAO sectionDAO;
    private static SectionRepository instance;
    private ArrayList<Section> list;

    public SectionRepository() {
        list = new ArrayList<>();
        sectionDAO = InventoryDatabase.getDatabase().sectionDAO();
    }

    @Override
    public void getList(OnRepositoryListCallback callback) throws ExecutionException, InterruptedException {
        try {
            this.list = (ArrayList<Section>) InventoryDatabase.databaseWriteExecutor.submit(() -> sectionDAO.select()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        callback.onSuccess(this.list);
    }

    @Override
    public void delete(Section section, OnRepositoryListCallback callback) {
        InventoryDatabase.databaseWriteExecutor.submit(() -> sectionDAO.delete(section));
        callback.onDeleteSuccess("Se ha eliminado la seccion: " + section.getName());
    }

    @Override
    public void undo(Section section, OnRepositoryListCallback callback) {
        InventoryDatabase.databaseWriteExecutor.submit(() -> sectionDAO.insert(section));
        callback.onUndoSuccess("Se ha recuperado la seccion: " + section.getName());
    }

    public static SectionRepository getInstance(){
        if (instance == null){
            instance = new SectionRepository();
        }

        return instance;
    }
}
