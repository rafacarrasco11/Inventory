package com.example.inventory.ui.section.manage;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.base.OnRepositoryManageCallback;

public interface SectionManageContract {
    interface View extends OnInteractorListener {
        void addSection(Section section);
        void editSection(Section sectionEdit, Section section);
    }

    interface Presenter {
        void add(Section section);
        void edit(Section sectionEdit, Section section);
    }

    interface Repository {
        void add(Section section, OnRepositoryManageCallback callback);
        void edit(Section sectionEdit, Section section, OnRepositoryManageCallback callback);
    }

    interface OnInteractorListener extends OnRepositoryManageCallback {

    }
}
