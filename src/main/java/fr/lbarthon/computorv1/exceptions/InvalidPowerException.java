package fr.lbarthon.computorv1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidPowerException extends Exception {
    double power;
}
