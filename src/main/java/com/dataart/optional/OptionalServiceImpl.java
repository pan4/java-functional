package com.dataart.optional;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import com.dataart.core.data.opt.Computer;
import com.dataart.core.data.opt.SoundCard;
import com.dataart.core.data.opt.USB;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class OptionalServiceImpl {

    public static void printIfPresent(Optional<Worker> worker) {
        worker.ifPresent(w -> System.out.println(w.getProfession()));
    }

    public static void printOrElse(Optional<Worker> worker) {
        System.out.println(worker.orElse(new Worker(1L, "Default", "Default", 0, Profession.PROGRAMMER)));
    }

    public static Optional<SoundCard> checkSoundCardOrSetDefault(Optional<Computer> computer) {
        return computer.flatMap(getUSBOrSetDefault).flatMap(getSoundCardOrSetDefault);
    }

    private static Function<Computer, Optional<USB>> getUSBOrSetDefault = computer -> {
        if (computer.getUsb().isPresent()) {
            return computer.getUsb();
        } else {
            computer.setUsb(Optional.of(new USB()));
            return computer.getUsb();
        }
    };

    private static Function<USB, Optional<SoundCard>> getSoundCardOrSetDefault = usb -> {
        if (usb.getSoundCard().isPresent()) {
            return usb.getSoundCard();
        } else {
            usb.setSoundCart(Optional.of(new SoundCard("CH External")));
            return usb.getSoundCard();
        }
    };


    public static Boolean isSoundCardPresent(Optional<Computer> computer) {
        return computer.flatMap(Computer::getUsb).flatMap(USB::getSoundCard).isPresent();
    }

    public static String getSoundCardInfo(Optional<Computer> computer) {
        return computer.flatMap(Computer::getUsb)
                .flatMap(USB::getSoundCard)
                .map(SoundCard::getVersion)
                .orElse("NONE");
    }

    public static Optional<Computer> getComputersByCriterias(Optional<Computer> computerOptional, Predicate p) {
        return computerOptional.filter(p);
    }
}
