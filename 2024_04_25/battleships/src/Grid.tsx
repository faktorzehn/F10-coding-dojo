// src/Grid.tsx
import React, {useEffect, useState} from 'react';
import './Grid.css';
import Notification from "./Notification";

const BOARD_SIZE = 10;
const VALIDATOR_API_URL = process.env.REACT_APP_VALIDATOR_API_URL!;

const Grid: React.FC = () => {
    function getInitialState() {
        return Array(BOARD_SIZE).fill(0, 0, BOARD_SIZE).map(() => Array(BOARD_SIZE).fill(0));
    }

    const [board, setBoard] = useState<number[][]>(getInitialState());
    const [showNotification, setShowNotification] = useState(false);
    const [notificationMessage, setNotificationMessage] = useState('This is a notification!');

    useEffect(() => {
        if (showNotification) {
            const timer = setTimeout(() => {
                setShowNotification(false);
            }, 5000); // Hide after 5 seconds

            return () => clearTimeout(timer); // Cleanup on unmount
        }
    }, [showNotification]);

    const handleShowNotification = () => {
        setShowNotification(true);
    };

    const handleMessageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setNotificationMessage(event.target.value);
    };

    const clearBoard = () => {
        setBoard(getInitialState());
    }

    const placeShips = () => {
        // Use the current board state instead of initializing a new empty board
        const newBoard = getInitialState();

        // Place ships
        const placeShip = (length: number, orientation: 'horizontal' | 'vertical') => {
            let placed = false;
            while (!placed) {
                const row = Math.floor(Math.random() * BOARD_SIZE);
                const col = Math.floor(Math.random() * BOARD_SIZE);
                const isHorizontal = orientation === 'horizontal';
                const start = isHorizontal ? col : row;
                const end = start + length - 1;

                // Function to check if a cell is within the board and not occupied
                const isCellValid = (r: number, c: number) => {
                    if (r < 0 || r >= BOARD_SIZE || c < 0 || c >= BOARD_SIZE) return false; // Check if within board bounds
                    return newBoard[r][c] === 0; // Check if cell is not occupied
                };

                // Check if the ship can be placed without going out of bounds and without being adjacent to another ship
                const isValid = isHorizontal
                    ? Array.from({length: length + 2}, (_, i) => isCellValid(row, col + i - 1))
                    : Array.from({length: length + 2}, (_, i) => isCellValid(row + i - 1, col));

                if (isValid.every(valid => valid)) {
                    for (let i = start; i <= end; i++) {
                        if (isHorizontal) {
                            newBoard[row][i] = 1;
                        } else {
                            newBoard[i][col] = 1;
                        }
                    }
                    placed = true;
                }
            }
        };


        const orientation = (): 'horizontal' | 'vertical' => {
            return Math.random() < 0.5 ? 'horizontal' : 'vertical';
        }
        // Place ships according to the rules
        placeShip(4, orientation()); // Battleship
        placeShip(3, orientation()); // Cruiser 1
        placeShip(3, orientation()); // Cruiser 2
        placeShip(2, orientation()); // Destroyer 1
        placeShip(2, orientation()); // Destroyer 2
        placeShip(2, orientation()); // Destroyer 3
        placeShip(1, orientation()); // Submarine 1
        placeShip(1, orientation()); // Submarine 2
        placeShip(1, orientation()); // Submarine 3
        placeShip(1, orientation()); // Submarine 4

        setBoard(newBoard);
    };

    // Function to validate the board by calling a REST endpoint
    const validateBoard = async () => {
        try {
            let body = JSON.stringify({board: board});
            const response = await fetch(VALIDATOR_API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: body,
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const isValid = await response.json();
            console.log('Validation result:', isValid);
            // Show notification based on the validation result
            setShowNotification(true);
            if (isValid) {
                setNotificationMessage('The board is valid!');
            } else {
                setNotificationMessage('The board is not valid. Please check your input.');
            }
            // Handle the validation result as needed
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            alert('There was a problem with the fetch operation');
        }
    };

    // Function to toggle a cell's state
    const toggleCell = (row: number, col: number) => {
        const newBoard = [...board];
        newBoard[row][col] = newBoard[row][col] === 0 ? 1 : 0;
        setBoard(newBoard);
    };


    return (
        <div className="app-container">
            <div className="button-container">
                <div className="button-group">
                    <button className="material-button" onClick={placeShips}>Place Ships</button>
                    <button className="material-button" onClick={clearBoard}>Clear Ships</button>
                    <button className="material-button" onClick={validateBoard}>Validate</button>
                </div>
            </div>
            {showNotification &&
                <Notification message={notificationMessage} onClose={() => setShowNotification(false)}/>}
            <div className="grid-container">
                {board.map((row, rowIndex) => (
                    <div key={rowIndex} className="grid-row">
                        {row.map((cell, cellIndex) => (
                            <div key={cellIndex}
                                 className={`grid-cell ${cell === 1 ? 'ship' : ''}`}
                                 onClick={() => toggleCell(rowIndex, cellIndex)}>
                            </div>
                        ))}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Grid;
