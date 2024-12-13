import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/room';

export const reservationService = {
    requestReservations: async (roomId) => {
        try {
            const response = await axios.get(`${API_BASE_URL}/${roomId}/reservation`, {
                withCredentials: true // HttpOnly 쿠키를 요청에 포함
            });
            return response.data;
        } catch (error) {
            console.error('Error fetching reservations:', error);
            throw error;
        }
    }
};