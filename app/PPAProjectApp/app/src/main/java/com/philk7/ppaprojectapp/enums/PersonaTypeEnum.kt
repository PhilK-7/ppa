package com.philk7.ppaprojectapp.enums

/**
 * An enum representing the different Privacy Personas.
 * ATTENTION: The index here starts at 1, not 0.
 * Also see string array 'pp_codes'.
 *
 * Meaning: At position 1 and 3, L/M/K stand for low/medium/high,
 *  while M/K at position 2 and 4 stand for motivation/knowledge.
 */
enum class PersonaTypeEnum(pindex: Int) {

    HMHK(1),  // Fundamentalist
    HMLK(2),  // Motivated Layman
    HMMK(3),  // Technician
    LMHK(4),  // Lazy Expert
    LMLK(5),  // Marginally Concerned
    LMMK(6),  // Lazy Amateur
    MMHK(7),  // Expert
    MMLK(8),  // Concerned Layman
    MMMK(9)  // Struggling Amateur
}