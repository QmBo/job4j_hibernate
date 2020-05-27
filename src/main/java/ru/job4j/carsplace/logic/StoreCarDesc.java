package ru.job4j.carsplace.logic;

import ru.job4j.Store;
import ru.job4j.carsplace.models.*;
import ru.job4j.hello.models.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * StoreCarDesc
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public interface StoreCarDesc extends Store<Car> {
    /**
     * Gets all makers.
     *
     * @return the all makers
     */
    List<KeyValue> getAllMakers();

    /**
     * Gets models of maker.
     *
     * @param maker the maker
     * @return the models of maker
     */
    List<KeyValue> getModelsOfMaker(Maker maker);

    /**
     * Gets gens of model.
     *
     * @param model the model
     * @return the gens of model
     */
    List<Generation> getGensOfModel(Model model);

    /**
     * Gets body by gen.
     *
     * @param generation the generation
     * @return the body by gen
     */
    List<KeyValue> getBodyByGen(Generation generation);

    /**
     * Gets engines by body.
     *
     * @param setId the set id
     * @return the engines by body
     */
    List<KeyValue> getEnginesByBody(Body setId);

    /**
     * Gets doors by body.
     *
     * @param setId the set id
     * @return the doors by body
     */
    List<KeyValue> getDoorsByBody(Body setId);

    /**
     * Gets gear box by engine.
     *
     * @param setId the set id
     * @return the gear box by engine
     */
    List<KeyValue> getGearBoxByEngine(Engine setId);

    /**
     * Gets drive by engine.
     *
     * @param setId the set id
     * @return the drive by engine
     */
    List<KeyValue> getDriveByEngine(Engine setId);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<User> getAllUsers();

    /**
     * Add add.
     *
     * @param add the add
     */
    void addOrUpdateAdd(Add add);

    /**
     * Add photos.
     *
     * @param photos the photos
     */
    void addPhotos(List<Photo> photos);

    /**
     * All add list.
     *
     * @return the list
     */
    List<Add> allAdd();

    /**
     * Add of maker list.
     *
     * @param setId the set id
     * @return the list
     */
    List<Add> addOfMaker(Maker setId);

    /**
     * Gets makers of add.
     *
     * @return the makers of add
     */
    List<KeyValue> getMakersOfAdd();

    /**
     * Gets add has photo.
     *
     * @return the add has photo
     */
    List<Add> getAddHasPhoto();

    /**
     * Gets add after date.
     *
     * @param date the date
     * @return the add after date
     */
    List<Add> getAddAfterDate(Timestamp date);
}
