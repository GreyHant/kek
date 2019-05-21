package exam;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement
public class Model {

    @XmlElementWrapper(name = "rules")
    @XmlElement(name = "rule")
    private List<Rule> rules;

    @XmlElementWrapper(name = "facts")
    @XmlElement(name = "fact")
    private Set<String> facts;

    public Model(Set<String> facts, List<Rule> rules) {
        this.facts = facts;
        this.rules = rules;
    }

    public Model() {
    }

    public void calculate() {
        int factsSize;
        do {
            factsSize = facts.size();
            for (Rule rule : rules) {
                rule.evaluate(facts);
            }
        } while (factsSize != facts.size());
    }


    public Set<String> getFacts() {
        return facts;
    }

    @Override
    public String toString() {
        return "Model{" +
                "rules=" + rules +
                ", facts=" + facts +
                '}';
    }

    public List<Rule> getRules() {
        return rules;
    }
}
