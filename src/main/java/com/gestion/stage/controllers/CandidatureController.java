package com.gestion.stage.controllers;

import com.gestion.stage.models.Candidature;
import com.gestion.stage.models.EmailDetails;
import com.gestion.stage.models.Offres;
import com.gestion.stage.models.User;
import com.gestion.stage.security.services.UserDetailsServiceImpl;
import com.gestion.stage.service.CandidatureServiceImp;
import com.gestion.stage.service.EmailServiceImp;
import com.gestion.stage.service.OffresServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/api/Candidature")
    public class CandidatureController {
        @Autowired
        private CandidatureServiceImp  candidatureServiceImp ;

        @Autowired
        private UserDetailsServiceImpl userDetailsServiceImpl;
        @Autowired
        private OffresServiceImp offresServiceImp;

         @Autowired
         private EmailServiceImp emailService;

//            @PreAuthorize( "hasRole('RH')")
//        @PostMapping("/save/{idST}/{idOff}")
//        public Candidature saveCandidateur(@RequestBody Candidature candidature, @PathVariable Long idST, @PathVariable Long idOff) {
//            User m1= userDetailsServiceImpl.getUserByID(idST);
//            Offres m2 =offresServiceImp.getOffreByID(idOff);
//            candidature.setUser(m1);
//            candidature.setOffres(m2);
//            return candidatureServiceImp.createCandidature(candidature);
//        }
//        @PostMapping("/save/{idST}/{idOff}")
//        public Candidature saveCandidateur(@RequestBody Candidature candidature, @PathVariable Long idST, @PathVariable Long idOff) {
//            User m1= userDetailsServiceImpl.getUserByID(idST);
//            Offres m2 =offresServiceImp.getOffreByID(idOff);
//            candidature.setUser(m1);
//            candidature.setOffres(m2);
//            Candidature savedCandidature = candidatureServiceImp.createCandidature(candidature);
//            // Send email notification to the candidate
//            String candidateEmail = m1.getEmail();
//            String offerName = m2.getIntitule();
//            sendEmailNotification(candidateEmail, offerName);
//            return savedCandidature;
//        }
//    private void sendEmailNotification(String recipientEmail, String offerName) {
//
//        EmailDetails emailDetails = new EmailDetails();
//        emailDetails.setRecipient(recipientEmail);
//        emailDetails.setSubject("Candidature Confirmation");
//        emailDetails.setMsgBody("Dear candidate,\n\nYou have successfully submitted your candidature for the offer: " + offerName + ".\n\nBest regards,\nThe Management Team");
//        emailService.sendSimpleMail(emailDetails);
//    }

    //    @PreAuthorize( "hasRole('RH')")
        @PostMapping("/save/{idST}/{idOff}")
        public Candidature saveCandidateur(@RequestBody Candidature candidature, @PathVariable Long idST, @PathVariable Long idOff) {
            User m1= userDetailsServiceImpl.getUserByID(idST);
            Offres m2 =offresServiceImp.getOffreByID(idOff);
            candidature.setUser(m1);
            candidature.setOffres(m2);
            Candidature savedCandidature = candidatureServiceImp.createCandidature(candidature);
            String  MsgBody = "Application Submitted for Offre : " + m2.getIntitule();
            String Subject = "Dear " + m1.getUsername() + ",\n\n" +
                    "Thank you for submitting your application for the position of " + m2.getIntitule() + " at our company.\n\n" +
                    "We have received your application and it is currently being reviewed by our hiring team. " +
                    "Please note that due to the high volume of applications we receive, this process may take some time.\n\n" +
                    "Rest assured that we will carefully consider your qualifications and experience. " +
                    "If your profile matches our requirements, we will reach out to you to discuss the next steps in the recruitment process.\n\n" +
                    "In the meantime, if you have any questions or need further assistance, feel free to contact us.\n\n" +
                    "Thank you once again for your interest in joining our team.\n\n" +
                    "Best regards,\n" +
                    "Ms. Clare Ayoub\n" +
                    "DRH\n" +
                    "Mike&San Company";
            EmailDetails emailDetails = new EmailDetails(m1.getEmail(), Subject, MsgBody);
            emailService.sendSimpleMail(emailDetails);
            return savedCandidature;
        }
    @GetMapping("/all")
    public List<Candidature> getAllCandidature() {
        return candidatureServiceImp.getAllCandidatures();
    }

    @GetMapping("/getone/{id}")
    public Candidature getoneCandidature(@PathVariable Long id) {
        return candidatureServiceImp.getCandidatureByID(id);
    }

    @PutMapping("/update/{id}")
    public Candidature updateCandidature(@PathVariable Long id, @RequestBody Candidature candidature) {
        Candidature C1 = candidatureServiceImp.getCandidatureByID(id);
        if (C1 != null) {
            candidature.setId(id);
            return candidatureServiceImp.updateCandidatureByID(candidature);
        } else {
            throw new RuntimeException("Failed to update! Candidature not found with ID: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCandidature(@PathVariable("id") long id) {
        try {
            candidatureServiceImp.deleteCandidature(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
