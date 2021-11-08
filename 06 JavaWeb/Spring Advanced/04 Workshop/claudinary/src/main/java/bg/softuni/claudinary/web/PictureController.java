package bg.softuni.claudinary.web;

import bg.softuni.claudinary.model.binding.PictureAddBindingModel;
import bg.softuni.claudinary.model.entity.PictureEntity;
import bg.softuni.claudinary.model.view.PictureViewModel;
import bg.softuni.claudinary.repository.PictureRepository;
import bg.softuni.claudinary.service.CloudinaryImage;
import bg.softuni.claudinary.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PictureController {
    private final CloudinaryService cloudinaryService;

    //do not use this directly in controller
    private final PictureRepository pictureRepository;

    public PictureController(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/pictures/all")
    public String all(Model model) {

        List<PictureViewModel> pictures = this.pictureRepository.findAll()
                .stream()
                .map(this::mapToViewModel)
                .collect(Collectors.toList());

        model.addAttribute("pictures", pictures);

        return "all";
    }
//
//    @GetMapping("/pictures/all")
//    public String all(Model model) {
//        List<PictureViewModel> pictures = pictureRepository.
//                findAll().
//                stream().
//                map(this::mapToViewModel).
//                collect(Collectors.toList());
//
//        model.addAttribute("pictures", pictures);
//
//        return "all";
//    }

    @GetMapping("/pictures/add")
    public String addPicture() {

        return "add";
    }

    @PostMapping("/pictures/add")
    public String addPicture(PictureAddBindingModel pictureAddBindingModel) throws IOException {
        PictureEntity pictureEntity = createPictureEntity(pictureAddBindingModel.getPicture(), pictureAddBindingModel.getTitle());
        pictureRepository.save(pictureEntity);

        return "redirect:/pictures/all";
    }



    private PictureViewModel mapToViewModel(PictureEntity p) {
        return new PictureViewModel().setPublicId(p.getPublicId()).setTitle(p.getTitle()).setUrl(p.getUrl());
    }

    @Transactional
    @DeleteMapping("/pictures/delete")
    public String deletePicture(@RequestParam("public_id") String publicId) {
        if (this.cloudinaryService.delete(publicId)) {
            this.pictureRepository.deleteAllByPublicId(publicId);
        }

        return "redirect:/pictures/all";
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl())
                .setTitle(title);

    }
}

