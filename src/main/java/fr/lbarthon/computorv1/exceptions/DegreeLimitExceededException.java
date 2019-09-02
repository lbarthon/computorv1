package fr.lbarthon.computorv1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DegreeLimitExceededException extends Exception {
    long degree;
}
