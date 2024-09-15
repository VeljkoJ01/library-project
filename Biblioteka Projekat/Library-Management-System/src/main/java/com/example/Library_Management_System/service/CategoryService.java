package com.example.Library_Management_System.service;
import com.example.Library_Management_System.entity.Category;
import com.example.Library_Management_System.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;



    // Preuzmi sve kategorije
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Preuzmi kategoriju po ID-u
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Sačuvaj ili ažuriraj kategoriju
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }


}
