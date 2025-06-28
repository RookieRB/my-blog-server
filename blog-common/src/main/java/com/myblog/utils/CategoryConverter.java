package com.myblog.utils;

import com.myblog.entity.Category;
import com.myblog.vo.CategoryVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class CategoryConverter {
    public static ArrayList<CategoryVO> convertToCategoryVOList(ArrayList<Category> categoryList) {
        HashMap<Long, CategoryVO> hashMap = new HashMap<>();
        ArrayList<CategoryVO> result = new ArrayList<>();
        LinkedList<Category> queue = new LinkedList<>();

        for (Category category : categoryList) {
            if (category.getParentId() == 0) {
                CategoryVO categoryVO = new CategoryVO();
                categoryVO.setCategoryId(category.getCategoryId());
                categoryVO.setAliasName(category.getAliasName());
                categoryVO.setChildren(new ArrayList<>());
                hashMap.put(categoryVO.getCategoryId(), categoryVO);
                result.add(categoryVO);
            } else {
                queue.add(category);
            }
        }

        while (!queue.isEmpty()) {
            Category currentCategory = queue.pollFirst();
            if (hashMap.containsKey(currentCategory.getParentId())) {
                CategoryVO parentCategoryVO = hashMap.get(currentCategory.getParentId());
                CategoryVO childCategoryVO = new CategoryVO();
                childCategoryVO.setCategoryId(currentCategory.getCategoryId());
                childCategoryVO.setAliasName(currentCategory.getAliasName());
                childCategoryVO.setChildren(new ArrayList<>());
                parentCategoryVO.getChildren().add(childCategoryVO);
                hashMap.put(currentCategory.getCategoryId(), childCategoryVO);
            } else {
                queue.add(currentCategory);
            }
        }

        return result;
    }

}
