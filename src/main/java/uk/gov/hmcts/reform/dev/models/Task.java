package uk.gov.hmcts.reform.dev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String title;
    private String description;
    private String status;
    private String dueDate;

    Task(){}

    Task(String titleIn, String descriptionIn, String statusIn, String dueDateIn){
        this.title = titleIn;
        this.description = descriptionIn != null ? descriptionIn : "";
        this.status = statusIn;
        this.dueDate = dueDateIn;
    }
}