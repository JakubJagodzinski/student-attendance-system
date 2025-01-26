import axios from 'axios';

// Tworzymy instancję axios z domyślnym ustawieniem baseURL
const api = axios.create({
  baseURL: 'http://localhost:8080', // Ustawienie backendu na porcie 8080
  timeout: 10000,  // Czas oczekiwania na odpowiedź
});

// Funkcja do pobrania listy obecności studenta
export const getAllAttendances = async () => {
  try {
    const response = await api.get(`/attendances`);
    return response.data;
  } catch (error) {
    console.error("Błąd podczas pobierania obecności:", error);
    throw error;
  }
};

// Funkcja do logowania studenta
export const loginStudent = async (studentIndexNumber) => {
  try {
    const response = await api.get(`/students/${studentIndexNumber}`);
    return response.data;
  } catch (error) {
    console.error("Błąd podczas logowania:", error);
    throw error;
  }
};

// Funkcja do pobrania grupy studenta
export const getGroupInfo = async (groupID) => {
    try {
      const response = await api.get(`/groups/${groupID}`);
      return response.data;
    } catch (error) {
      console.error("Błąd podczas pobierania grupy:", error);
      throw error;
    }
};

// Funkcja do pobrania zajęć studenta
export const getTerms = async () => {
    try {
      const response = await api.get(`/terms`);
      return response.data;
    } catch (error) {
      console.error("Błąd podczas pobierania zajęć:", error);
      throw error;
    }
};