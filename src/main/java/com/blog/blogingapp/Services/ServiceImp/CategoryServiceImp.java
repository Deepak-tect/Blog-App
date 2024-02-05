package com.blog.blogingapp.Services.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogingapp.Entities.Category;
import com.blog.blogingapp.Execptions.ResourceNotFoundExecption;
import com.blog.blogingapp.Payloads.ResponseCategory;
import com.blog.blogingapp.Repositories.CategoryRepo;
import com.blog.blogingapp.Services.CategoryService;


@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    Category responseCategoryToCategory(ResponseCategory responseCategory){
        return this.modelMapper.map(responseCategory, Category.class);
    }
    ResponseCategory categoryToResponseCategory(Category category){
        return this.modelMapper.map(category, ResponseCategory.class);
    }

    @Override
    public ResponseCategory createCategory(ResponseCategory responseCategory) {
        Category category = responseCategoryToCategory(responseCategory);
        Category newCategory = this.categoryRepo.save(category);
        return categoryToResponseCategory(newCategory);
    }

    @Override
    public ResponseCategory updateCategory(ResponseCategory responseCategory, int id) {
        Optional<Category> optional = this.categoryRepo.findById(id);
        if(optional.isPresent()){
            Category category = optional.get();
            category.setTitle(responseCategory.getTitle());
            category.setDescription(responseCategory.getDescription());
            this.categoryRepo.save(category);
            return categoryToResponseCategory(category); 
        }
        throw new ResourceNotFoundExecption("Category", "id", id);
    }

    @Override
    public ResponseCategory getCategoryById(int id) {
        Optional<Category> optional = this.categoryRepo.findById(id);
        if(optional.isPresent()){
            return categoryToResponseCategory(optional.get());
        }
        throw new ResourceNotFoundExecption("Category", "id", id);
    }

    @Override
    public List<ResponseCategory> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        if(categories.size() > 0){
            List<ResponseCategory> responseCat = new ArrayList<ResponseCategory>();
            for(Category category : categories){
                responseCat.add(categoryToResponseCategory(category));
            }
            return responseCat;
        }
        throw new  ResourceNotFoundExecption("Category", "", 0);
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> optionalCat = categoryRepo.findById(id);
        if(optionalCat.isPresent()){
            categoryRepo.delete(optionalCat.get());
            return ;
        }
        throw new ResourceNotFoundExecption("Category", "id", id);

    }
    
}
