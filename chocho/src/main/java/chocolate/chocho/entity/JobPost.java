package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store   store;
    private String  title;
    private String  body;
}
