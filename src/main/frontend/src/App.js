import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from 'axios';
import MainPage from './pages/MainPage';

export const RoomContext = React.createContext();

function App() {
    const [rooms, setRooms] = useState([]);
    const [roomDetails, setRoomDetails] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchRoomsAndDetails = async () => {
            try {
                const roomsResponse = await axios.get('http://localhost:8080/room/list');
                if (roomsResponse.data.result === "success" && roomsResponse.data.data.rooms) {
                    const fetchedRooms = roomsResponse.data.data.rooms;
                    setRooms(fetchedRooms);

                    const detailsPromises = fetchedRooms.map(room =>
                        axios.get(`http://localhost:8080/room/${room.id}`)
                    );
                    const detailsResponses = await Promise.all(detailsPromises);

                    const details = {};
                    detailsResponses.forEach(response => {
                        if (response.data.result === "success") {
                            details[response.data.data.id] = response.data.data;
                        }
                    });
                    setRoomDetails(details);
                }
            } catch (error) {
                console.error('데이터 가져오기 중 오류 발생:', error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchRoomsAndDetails();
    }, []);

    return (
        <RoomContext.Provider value={{ rooms, roomDetails, isLoading }}>
            <Router>
                <Routes>
                    <Route path="/" element={<MainPage />} />
                </Routes>
            </Router>
        </RoomContext.Provider>
    );
}

export default App;
