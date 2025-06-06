import React, {useState, useEffect, useContext} from 'react';
import {useNavigate} from 'react-router-dom';
import {RoomContext} from '../App';
import { reservationService } from '../js/reservationService';
import axios from 'axios';
import * as bootstrap from 'bootstrap';
import '../styles/styles.css';
import '../js/scripts.js'
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import NIH0_01 from '../img/NIHo_01.png';
import testRoom_01 from '../img/testRoom_01.png'
import close_icon from '../img/close-icon.svg'

function MainPage() {
    const [loginData, setLoginData] = useState({email: '', password: ''});
    const [signupData, setSignupData] = useState({email: '', password: '', username: '', confirmPassword: ''});
    const {rooms, roomDetails, isLoading} = useContext(RoomContext);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const handleRoomSelect = (room) => {
        setSelectedRoom(room);
    };
    const handleLoginChange = (e) => {
        setLoginData({...loginData, [e.target.name]: e.target.value});
    };
    const handleSignupChange = (e) => {
        setSignupData({...signupData, [e.target.name]: e.target.value});
    };
    const navigate = useNavigate();

    const resetLoginForm = () => {
        setLoginData({email: '', password: ''});
    };

    const resetSignupForm = () => {
        setSignupData({email: '', password: '', username: '', confirmPassword: ''});
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/login',
                loginData, {
                    withCredentials: true
                });
            console.log('Login successful:', response.data);
            alert('login completed successfully!');
            navigate('/');
            const modal = document.getElementById('loginModal');
            const modalInstance = bootstrap.Modal.getInstance(modal);
            modalInstance.hide();
        } catch (error) {
            console.error('Login failed:', error);

            if (error.response && error.response.status === 400) {
                const errorData = error.response.data;
                let errorMessage = 'Login failed: ';

                switch (errorData.errorCode) {
                    case 'U0001':
                        errorMessage += '존재하지 않는 사용자입니다.';
                        break;
                    case 'l0001':
                        errorMessage += '아이디 혹은 비밀번호가 일치하지 않습니다.';
                        break;
                    default:
                        errorMessage += errorData.errorMsg || '알 수 없는 오류가 발생했습니다.';
                }

                const errorElement = document.getElementById('loginErrorMessage');
                if (errorElement) {
                    errorElement.textContent = errorMessage;
                    errorElement.style.display = 'block';
                } else {
                    alert(errorMessage);
                }
            } else {
                alert('로그인에 실패했습니다. 다시 시도해 주세요.');
            }
        }
    };

    const handleSignup = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/register', signupData);
            console.log('Signup successful:', response.data);
            alert('Signup completed successfully!');

            // 회원가입 모달 닫기
            const signupModal = document.getElementById('signupModal');
            const signupModalInstance = bootstrap.Modal.getInstance(signupModal);
            signupModalInstance.hide();

            // 로그인 모달 열기
            const loginModal = document.getElementById('loginModal');
            const loginModalInstance = new bootstrap.Modal(loginModal);
            loginModalInstance.show();

        } catch (error) {
            console.error('Signup failed:', error);

            if (error.response && error.response.status === 400) {
                const errorData = error.response.data;
                let errorMessage = 'Signup failed: ';

                switch (errorData.errorCode) {
                    case 'U0002':
                        errorMessage += '이미 사용 중인 이메일입니다.';
                        break;
                    case 'U0003':
                        errorMessage += '비밀번호가 일치하지 않습니다.';
                        break;
                    default:
                        errorMessage += errorData.errorMsg || '알 수 없는 오류가 발생했습니다.';
                }

                // 에러 메시지를 화면에 표시
                const errorElement = document.getElementById('signupErrorMessage');
                if (errorElement) {
                    errorElement.textContent = errorMessage;
                    errorElement.style.display = 'block';
                } else {
                    alert(errorMessage);
                }
            } else {
                alert('회원가입에 실패했습니다. 다시 시도해 주세요.');
            }
        }
    };

    const handleBookNow = async (roomId) => {
        try {
            const result = await reservationService.requestReservations(roomId);
            console.log('Reservation request sent:', result);
            // 여기에 예약 성공 시 처리 로직 추가 (예: 알림 표시)
        } catch (error) {
            console.error('Failed to request reservation:', error);
            // 여기에 예약 실패 시 처리 로직 추가 (예: 에러 메시지 표시)
        }
    };

    useEffect(() => {
        if (rooms.length > 0) {
            setSelectedRoom(rooms[0]);
        }
    }, [rooms]);

    useEffect(() => {
        // 모달 이벤트 리스너 추가
        const loginModal = document.getElementById('loginModal');
        const signupModal = document.getElementById('signupModal');

        const handleLoginModalHidden = () => resetLoginForm();
        const handleSignupModalHidden = () => resetSignupForm();

        loginModal.addEventListener('hidden.bs.modal', handleLoginModalHidden);
        signupModal.addEventListener('hidden.bs.modal', handleSignupModalHidden);

        // 클린업 함수
        return () => {
            loginModal.removeEventListener('hidden.bs.modal', handleLoginModalHidden);
            signupModal.removeEventListener('hidden.bs.modal', handleSignupModalHidden);
        };
    }, []);

    return (
        <>
            {/* Navigation*/}
            <nav className="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
                <div className="container">
                    <a className="navbar-brand" href="#">
                        <img src={NIH0_01} alt="..."/>
                    </a>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarResponsive"
                        aria-controls="navbarResponsive"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        Menu
                        <i className="fas fa-bars ms-1"/>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarResponsive">
                        <ul className="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                            <li className="nav-item">
                                <a className="nav-link" href="#amenity">
                                    Amenity
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#bookRoom">
                                    Book Room
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#about">
                                    About
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#team">
                                    Team
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#contact">
                                    Contact
                                </a>
                            </li>
                            <li className="nav-item">
                                <a
                                    className="nav-link"
                                    href="#"
                                    data-bs-toggle="modal"
                                    data-bs-target="#loginModal"
                                >
                                    SignIn
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            {/* Masthead*/}
            <header className="masthead">
                <div className="container">
                    <div className="masthead-subheading">Welcome To Hotel NIHo</div>
                    <div className="masthead-heading text-uppercase">
                        Nature Inspired Hotel
                    </div>
                    <div className="masthead-subheading">Resort &amp; Spa Hotel</div>
                    <a className="btn btn-primary btn-xl text-uppercase" href="#bookRoom">
                        Book Room
                    </a>
                </div>
            </header>
            {/* Amenity Boutique*/}
            <section className="page-section" id="amenity">
                <div className="container">
                    <div className="text-center">
                        <h2 className="section-heading text-uppercase">Amenity Boutique</h2>
                        <h3 className="section-subheading text-muted">
                            Lorem ipsum dolor sit amet consectetur.
                        </h3>
                    </div>
                    <div className="row text-center">
                        <div className="col-md-4">
          <span className="fa-stack fa-4x">
            <i className="fas fa-circle fa-stack-2x text-primary"/>
            <i className="fas fa-shopping-cart fa-stack-1x fa-inverse"/>
          </span>
                            <h4 className="my-3">E-Commerce</h4>
                            <p className="text-muted">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima
                                maxime quam architecto quo inventore harum ex magni, dicta impedit.
                            </p>
                        </div>
                        <div className="col-md-4">
          <span className="fa-stack fa-4x">
            <i className="fas fa-circle fa-stack-2x text-primary"/>
            <i className="fas fa-laptop fa-stack-1x fa-inverse"/>
          </span>
                            <h4 className="my-3">Responsive Design</h4>
                            <p className="text-muted">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima
                                maxime quam architecto quo inventore harum ex magni, dicta impedit.
                            </p>
                        </div>
                        <div className="col-md-4">
          <span className="fa-stack fa-4x">
            <i className="fas fa-circle fa-stack-2x text-primary"/>
            <i className="fas fa-lock fa-stack-1x fa-inverse"/>
          </span>
                            <h4 className="my-3">Web Security</h4>
                            <p className="text-muted">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima
                                maxime quam architecto quo inventore harum ex magni, dicta impedit.
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            {/*Book Room*/}
            <section className="page-section bg-light" id="bookRoom">
                <div className="container">
                    <div className="text-center">
                        <h2 className="section-heading text-uppercase">Book Room</h2>
                        <h3 className="section-subheading text-muted">
                            Lorem ipsum dolor sit amet consectetur.
                        </h3>
                    </div>
                    {isLoading ? (
                        <p>Loading...</p>
                    ) : (
                        <div className="row">
                            {rooms.map((room) => (
                                <div className="col-lg-4 col-sm-6 mb-4" key={room.id}>
                                    {/* Room Item */}
                                    <div className="room-item">
                                        <a
                                            className="room-link"
                                            data-bs-toggle="modal"
                                            href={`#roomModal${room.id}`}
                                        >
                                            <div className="room-hover">
                                                <div className="room-hover-content">
                                                    <i className="fas fa-plus fa-3x"/>
                                                </div>
                                            </div>
                                            <img
                                                className="img-fluid"
                                                src={testRoom_01}
                                                alt={room.roomName}
                                            />
                                        </a>
                                        <div className="room-caption">
                                            <div className="room-caption-heading">{room.roomName}</div>
                                            <div className="room-caption-subheading text-muted">
                                                Capacity: {room.capacity}, Grade: {room.roomGrade}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </div>
            </section>


            <section className="page-section bg-light" id="bookRoom">
                <div className="container">
                    <div className="text-center mb-5">
                        <h2 className="section-heading text-uppercase">Book Room</h2>
                        <h3 className="section-subheading text-muted">
                            Lorem ipsum dolor sit amet consectetur.
                        </h3>
                    </div>
                    {isLoading ? (
                        <p>Loading...</p>
                    ) : (
                        <>
                            {/* Main Room Display */}
                            <div className="row mb-5">
                                <div className="col-md-6">
                                    {/*<img className="img-fluid" src={selectedRoom.image} alt={selectedRoom.roomName}/>*/}
                                    <img className="img-fluid" src={testRoom_01} alt={selectedRoom.roomName}/>
                                </div>
                                <div className="col-md-6">
                                    <h3>{selectedRoom.roomName}</h3>
                                    <p><strong>Capacity:</strong> {roomDetails[selectedRoom.id]?.capacity} persons</p>
                                    <p><strong>Grade:</strong> {roomDetails[selectedRoom.id]?.roomGrade}</p>
                                    <p><strong>Price:</strong> ${roomDetails[selectedRoom.id]?.price} per night</p>
                                    <p>{selectedRoom.description}</p>
                                    <button
                                        className="btn btn-primary"
                                        onClick={() => handleBookNow(selectedRoom.id)}
                                    >
                                        Book Now
                                    </button>
                                </div>
                            </div>

                            {/* Other Rooms List */}
                            <div className="row">
                                <h4 className="mb-3">Other Available Rooms</h4>
                                {rooms.map((room) => (
                                    <div className="col-md-2 mb-4" key={room.id}>
                                        <div className="room-item" onClick={() => handleRoomSelect(room)}>
                                            {/*<img className="img-fluid" src={room.image} alt={room.roomName}/>*/}
                                            <img className="img-fluid" src={testRoom_01} alt={room.roomName}/>
                                            <div className="room-caption-heading">{room.roomName}</div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </>
                    )}
                </div>
            </section>


            {/* About*/}
            <section className="page-section" id="about">
                <div className="container">
                    <div className="text-center">
                        <h2 className="section-heading text-uppercase">About</h2>
                        <h3 className="section-subheading text-muted">
                            Lorem ipsum dolor sit amet consectetur.
                        </h3>
                    </div>
                    <ul className="timeline">
                        <li>
                            <div className="timeline-image">
                                <img
                                    className="rounded-circle img-fluid"
                                    src="../img/about/1.jpg"
                                    alt="..."
                                />
                            </div>
                            <div className="timeline-panel">
                                <div className="timeline-heading">
                                    <h4>2009-2011</h4>
                                    <h4 className="subheading">Our Humble Beginnings</h4>
                                </div>
                                <div className="timeline-body">
                                    <p className="text-muted">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt
                                        ut voluptatum eius sapiente, totam reiciendis temporibus qui
                                        quibusdam, recusandae sit vero unde, sed, incidunt et ea quo
                                        dolore laudantium consectetur!
                                    </p>
                                </div>
                            </div>
                        </li>
                        <li className="timeline-inverted">
                            <div className="timeline-image">
                                <img
                                    className="rounded-circle img-fluid"
                                    src="../img/about/2.jpg"
                                    alt="..."
                                />
                            </div>
                            <div className="timeline-panel">
                                <div className="timeline-heading">
                                <h4>March 2011</h4>
                                    <h4 className="subheading">An Agency is Born</h4>
                                </div>
                                <div className="timeline-body">
                                    <p className="text-muted">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt
                                        ut voluptatum eius sapiente, totam reiciendis temporibus qui
                                        quibusdam, recusandae sit vero unde, sed, incidunt et ea quo
                                        dolore laudantium consectetur!
                                    </p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div className="timeline-image">
                                <img
                                    className="rounded-circle img-fluid"
                                    src="../img/about/3.jpg"
                                    alt="..."
                                />
                            </div>
                            <div className="timeline-panel">
                                <div className="timeline-heading">
                                    <h4>December 2015</h4>
                                    <h4 className="subheading">Transition to Full Service</h4>
                                </div>
                                <div className="timeline-body">
                                    <p className="text-muted">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt
                                        ut voluptatum eius sapiente, totam reiciendis temporibus qui
                                        quibusdam, recusandae sit vero unde, sed, incidunt et ea quo
                                        dolore laudantium consectetur!
                                    </p>
                                </div>
                            </div>
                        </li>
                        <li className="timeline-inverted">
                            <div className="timeline-image">
                                <img
                                    className="rounded-circle img-fluid"
                                    src="../img/about/4.jpg"
                                    alt="..."
                                />
                            </div>
                            <div className="timeline-panel">
                                <div className="timeline-heading">
                                    <h4>July 2020</h4>
                                    <h4 className="subheading">Phase Two Expansion</h4>
                                </div>
                                <div className="timeline-body">
                                    <p className="text-muted">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt
                                        ut voluptatum eius sapiente, totam reiciendis temporibus qui
                                        quibusdam, recusandae sit vero unde, sed, incidunt et ea quo
                                        dolore laudantium consectetur!
                                    </p>
                                </div>
                            </div>
                        </li>
                        <li className="timeline-inverted">
                            <div className="timeline-image">
                                <h4>
                                    Be Part
                                    <br/>
                                    Of Our
                                    <br/>
                                    Story!
                                </h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </section>
            {/* Team*/}
            <section className="page-section bg-light" id="team">
                <div className="container">
                    <div className="text-center">
                        <h2 className="section-heading text-uppercase">Our Amazing Team</h2>
                        <h3 className="section-subheading text-muted">
                            Lorem ipsum dolor sit amet consectetur.
                        </h3>
                    </div>
                    <div className="row">
                        <div className="col-lg-4">
                            <div className="team-member">
                                <img
                                    className="mx-auto rounded-circle"
                                    src="../img/team/1.jpg"
                                    alt="..."
                                />
                                <h4>Parveen Anand</h4>
                                <p className="text-muted">Lead Designer</p>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Parveen Anand Twitter Profile"
                                >
                                    <i className="fab fa-twitter"/>
                                </a>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Parveen Anand Facebook Profile"
                                >
                                    <i className="fab fa-facebook-f"/>
                                </a>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Parveen Anand LinkedIn Profile"
                                >
                                    <i className="fab fa-linkedin-in"/>
                                </a>
                            </div>
                        </div>
                        <div className="col-lg-4">
                            <div className="team-member">
                                <img
                                    className="mx-auto rounded-circle"
                                    src="../img/team/2.jpg"
                                    alt="..."
                                />
                                <h4>Diana Petersen</h4>
                                <p className="text-muted">Lead Marketer</p>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Diana Petersen Twitter Profile"
                                >
                                    <i className="fab fa-twitter"/>
                                </a>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Diana Petersen Facebook Profile"
                                >
                                    <i className="fab fa-facebook-f"/>
                                </a>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Diana Petersen LinkedIn Profile"
                                >
                                    <i className="fab fa-linkedin-in"/>
                                </a>
                            </div>
                        </div>
                        <div className="col-lg-4">
                            <div className="team-member">
                                <img
                                    className="mx-auto rounded-circle"
                                    src="../img/team/3.jpg"
                                    alt="..."
                                />
                                <h4>Larry Parker</h4>
                                <p className="text-muted">Lead Developer</p>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Larry Parker Twitter Profile"
                                >
                                    <i className="fab fa-twitter"/>
                                </a>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Larry Parker Facebook Profile"
                                >
                                    <i className="fab fa-facebook-f"/>
                                </a>
                                <a
                                    className="btn btn-dark btn-social mx-2"
                                    href="#!"
                                    aria-label="Larry Parker LinkedIn Profile"
                                >
                                    <i className="fab fa-linkedin-in"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg-8 mx-auto text-center">
                            <p className="large text-muted">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut eaque,
                                laboriosam veritatis, quos non quis ad perspiciatis, totam corporis
                                ea, alias ut unde.
                            </p>
                        </div>
                    </div>
                </div>
            </section>
            {/* Clients*/}
            <div className="py-5">
                <div className="container">
                    <div className="row align-items-center">
                        <div className="col-md-3 col-sm-6 my-3">
                            <a href="#!">
                                <img
                                    className="img-fluid img-brand d-block mx-auto"
                                    src="../img/logos/microsoft.svg"
                                    alt="..."
                                    aria-label="Microsoft Logo"
                                />
                            </a>
                        </div>
                        <div className="col-md-3 col-sm-6 my-3">
                            <a href="#!">
                                <img
                                    className="img-fluid img-brand d-block mx-auto"
                                    src="../img/logos/google.svg"
                                    alt="..."
                                    aria-label="Google Logo"
                                />
                            </a>
                        </div>
                        <div className="col-md-3 col-sm-6 my-3">
                            <a href="#!">
                                <img
                                    className="img-fluid img-brand d-block mx-auto"
                                    src="../img/logos/facebook.svg"
                                    alt="..."
                                    aria-label="Facebook Logo"
                                />
                            </a>
                        </div>
                        <div className="col-md-3 col-sm-6 my-3">
                            <a href="#!">
                                <img
                                    className="img-fluid img-brand d-block mx-auto"
                                    src="../img/logos/ibm.svg"
                                    alt="..."
                                    aria-label="IBM Logo"
                                />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            {/* Contact*/}
            <section className="page-section" id="contact">
                <div className="container">
                    <div className="text-center">
                        <h2 className="section-heading text-uppercase">Contact Us</h2>
                        <h3 className="section-subheading text-muted">
                            Lorem ipsum dolor sit amet consectetur.
                        </h3>
                    </div>
                    {/* * * * * * * * * * * * * * * **/}
                    {/* * * SB Forms Contact Form * **/}
                    {/* * * * * * * * * * * * * * * **/}
                    {/* This form is pre-integrated with SB Forms.*/}
                    {/* To make this form functional, sign up at*/}
                    {/* https://startbootstrap.com/solution/contact-forms*/}
                    {/* to get an API token!*/}
                    <form id="contactForm" data-sb-form-api-token="API_TOKEN">
                        <div className="row align-items-stretch mb-5">
                            <div className="col-md-6">
                                <div className="form-group">
                                    {/* Name input*/}
                                    <input
                                        className="form-control"
                                        id="name"
                                        type="text"
                                        placeholder="Your Name *"
                                        data-sb-validations="required"
                                    />
                                    <div
                                        className="invalid-feedback"
                                        data-sb-feedback="name:required"
                                    >
                                        A name is required.
                                    </div>
                                </div>
                                <div className="form-group">
                                    {/* Email address input*/}
                                    {/*                                <input class="form-control" id="email" type="email" placeholder="Your Email *" data-sb-validations="required,email" />*/}
                                    <div
                                        className="invalid-feedback"
                                        data-sb-feedback="email:required"
                                    >
                                        An email is required.
                                    </div>
                                    <div className="invalid-feedback" data-sb-feedback="email:email">
                                        Email is not valid.
                                    </div>
                                </div>
                                <div className="form-group mb-md-0">
                                    {/* Phone number input*/}
                                    <input
                                        className="form-control"
                                        id="phone"
                                        type="tel"
                                        placeholder="Your Phone *"
                                        data-sb-validations="required"
                                    />
                                    <div
                                        className="invalid-feedback"
                                        data-sb-feedback="phone:required"
                                    >
                                        A phone number is required.
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group form-group-textarea mb-md-0">
                                    {/* Message input*/}
                                    <textarea
                                        className="form-control"
                                        id="message"
                                        placeholder="Your Message *"
                                        data-sb-validations="required"
                                        defaultValue={""}
                                    />
                                    <div
                                        className="invalid-feedback"
                                        data-sb-feedback="message:required"
                                    >
                                        A message is required.
                                    </div>
                                </div>
                            </div>
                        </div>
                        {/* Submit success message*/}
                        {/**/}
                        {/* This is what your users will see when the form*/}
                        {/* has successfully submitted*/}
                        <div className="d-none" id="submitSuccessMessage">
                            <div className="text-center text-white mb-3">
                                <div className="fw-bolder">Form submission successful!</div>
                                To activate this form, sign up at
                                <br/>
                                <a href="https://startbootstrap.com/solution/contact-forms">
                                    https://startbootstrap.com/solution/contact-forms
                                </a>
                            </div>
                        </div>
                        {/* Submit error message*/}
                        {/**/}
                        {/* This is what your users will see when there is*/}
                        {/* an error submitting the form*/}
                        <div className="d-none" id="submitErrorMessage">
                            <div className="text-center text-danger mb-3">
                                Error sending message!
                            </div>
                        </div>
                        {/* Submit Button*/}
                        <div className="text-center">
                            <button
                                className="btn btn-primary btn-xl text-uppercase disabled"
                                id="submitButton"
                                type="submit"
                            >
                                Send Message
                            </button>
                        </div>
                    </form>
                </div>
            </section>
            {/* Footer*/}
            <footer className="footer py-4">
                <div className="container">
                    <div className="row align-items-center">
                        <div className="col-lg-4 text-lg-start">
                            Copyright © Your Website 2023
                        </div>
                        <div className="col-lg-4 my-3 my-lg-0">
                            <a
                                className="btn btn-dark btn-social mx-2"
                                href="#!"
                                aria-label="Twitter"
                            >
                                <i className="fab fa-twitter"/>
                            </a>
                            <a
                                className="btn btn-dark btn-social mx-2"
                                href="#!"
                                aria-label="Facebook"
                            >
                                <i className="fab fa-facebook-f"/>
                            </a>
                            <a
                                className="btn btn-dark btn-social mx-2"
                                href="#!"
                                aria-label="LinkedIn"
                            >
                                <i className="fab fa-linkedin-in"/>
                            </a>
                        </div>
                        <div className="col-lg-4 text-lg-end">
                            <a className="link-dark text-decoration-none me-3" href="#!">
                                Privacy Policy
                            </a>
                            <a className="link-dark text-decoration-none" href="#!">
                                Terms of Use
                            </a>
                        </div>
                    </div>
                </div>
            </footer>

            {/* Login Modal */}
            <div className="modal fade" id="loginModal" tabIndex="-1" aria-labelledby="loginModalLabel"
                 aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="loginModalLabel">Login</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleLogin}>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label">E-mail</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="loginEmail"
                                        name="email"
                                        value={loginData.email}
                                        onChange={handleLoginChange}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label">Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="loginPassword"
                                        name="password"
                                        value={loginData.password}
                                        onChange={handleLoginChange}
                                        required
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary">Login</button>
                            </form>
                            <p className="text-center">
                                New here?{" "}
                                <a
                                    href="#"
                                    data-bs-toggle="modal"
                                    data-bs-target="#signupModal"
                                    data-bs-dismiss="modal"
                                >
                                    Sign up now
                                </a>
                            </p>

                        </div>
                    </div>
                </div>
            </div>
            {/* SignUp Modal */}
            <div className="modal fade" id="signupModal" tabIndex="-1" aria-labelledby="signupModalLabel"
                 aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="signupModalLabel">SignUp</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSignup}>
                                <div className="mb-3">
                                    <label htmlFor="signupUsername" className="form-label">Username</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="signupUsername"
                                        name="username"
                                        value={signupData.username}
                                        onChange={handleSignupChange}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="signupEmail" className="form-label">E-mail</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="signupEmail"
                                        name="email"
                                        value={signupData.email}
                                        onChange={handleSignupChange}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="signupPassword" className="form-label">Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="signupPassword"
                                        name="password"
                                        value={signupData.password}
                                        onChange={handleSignupChange}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="confirmPassword"
                                        name="confirmPassword"
                                        value={signupData.confirmPassword}
                                        onChange={handleSignupChange}
                                        required
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary">SignUp</button>
                            </form>
                            <p className="text-center">
                                Already have an account?{" "}
                                <a
                                    href="#"
                                    data-bs-toggle="modal"
                                    data-bs-target="#loginModal"
                                    data-bs-dismiss="modal"
                                >
                                    SignIn
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>


            {/* Room Modals */}
            {rooms.map((room) => (
                <div
                    key={room.id}
                    className="room-modal modal fade"
                    id={`roomModal${room.id}`}
                    tabIndex={-1}
                    role="dialog"
                    aria-hidden="true"
                >
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="close-modal" data-bs-dismiss="modal">
                                <img src={close_icon} alt="Close modal"/>
                            </div>
                            <div className="container">
                                <div className="row justify-content-center">
                                    <div className="col-lg-8">
                                        <div className="modal-body">
                                            <h2 className="text-uppercase">{room.roomName}</h2>
                                            <p className="item-intro text-muted">
                                                Room Grade: {room.roomGrade}
                                            </p>
                                            <img
                                                className="img-fluid d-block mx-auto"
                                                src={testRoom_01}
                                                alt={room.roomName}
                                            />
                                            <p>
                                                This {room.roomName} offers comfortable accommodation for up
                                                to {room.capacity} guests.
                                                It's a perfect choice for those seeking
                                                a {room.roomGrade === 1 ? 'standard' : 'luxurious'} stay.
                                            </p>
                                            <ul className="list-inline">
                                                <li>
                                                    <strong>Capacity:</strong> {room.capacity} persons
                                                </li>
                                                <li>
                                                    <strong>Price:</strong> ${roomDetails[room.id]?.price || 'N/A'} per night
                                                </li>
                                            </ul>
                                            {/*<button*/}
                                            {/*    className="btn btn-primary btn-xl text-uppercase"*/}
                                            {/*    type="button"*/}
                                            {/*    onClick={() => handleBooking(room.id)}  // 예약 처리 함수 추가 필요*/}
                                            {/*>*/}
                                            {/*    <i className="fas fa-check me-1"/>*/}
                                            {/*    Book Now*/}
                                            {/*</button>*/}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            ))}


            {/*/!* Room Modals *!/*/}
            {/*{rooms.map((room) => (*/}
            {/*    <div*/}
            {/*        key={room.id}*/}
            {/*        className="room-modal modal fade"*/}
            {/*        id={`roomModal${room.id}`}*/}
            {/*        tabIndex={-1}*/}
            {/*        role="dialog"*/}
            {/*        aria-hidden="true"*/}
            {/*    >*/}
            {/*        <div className="modal-dialog">*/}
            {/*            <div className="modal-content">*/}
            {/*                <div className="close-modal" data-bs-dismiss="modal">*/}
            {/*                    <img src={close_icon} alt="Close modal"/>*/}
            {/*                </div>*/}
            {/*                <div className="container">*/}
            {/*                    <div className="row justify-content-center">*/}
            {/*                        <div className="col-lg-8">*/}
            {/*                            <div className="modal-body">*/}
            {/*                                <h2 className="text-uppercase">{room.roomName}</h2>*/}
            {/*                                <p className="item-intro text-muted">*/}
            {/*                                    Room Grade: {room.roomGrade}*/}
            {/*                                </p>*/}
            {/*                                <img*/}
            {/*                                    className="img-fluid d-block mx-auto"*/}
            {/*                                    src={testRoom_01}*/}
            {/*                                    alt={room.roomName}*/}
            {/*                                />*/}
            {/*                                <p>*/}
            {/*                                    This {room.roomName} offers comfortable accommodation for up*/}
            {/*                                    to {room.capacity} guests.*/}
            {/*                                    It's a perfect choice for those seeking*/}
            {/*                                    a {room.roomGrade === 1 ? 'standard' : 'luxurious'} stay.*/}
            {/*                                </p>*/}
            {/*                                <ul className="list-inline">*/}
            {/*                                    <li>*/}
            {/*                                        <strong>Capacity:</strong> {room.capacity} persons*/}
            {/*                                    </li>*/}
            {/*                                    <li>*/}
            {/*                                        <strong>Price:</strong> ${roomDetails[room.id]?.price || 'N/A'} per*/}
            {/*                                        night*/}
            {/*                                    </li>*/}
            {/*                                </ul>*/}
            {/*                                <button*/}
            {/*                                    className="btn btn-primary btn-xl text-uppercase"*/}
            {/*                                    data-bs-dismiss="modal"*/}
            {/*                                    type="button"*/}
            {/*                                >*/}
            {/*                                    <i className="fas fa-xmark me-1"/>*/}
            {/*                                    Close Details*/}
            {/*                                </button>*/}
            {/*                            </div>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
            {/*        </div>*/}
            {/*    </div>*/}
            {/*))}*/}
        </>
    );
}

export default MainPage;