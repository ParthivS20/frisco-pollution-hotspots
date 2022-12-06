import {useEffect, useState} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import netlifyIdentity from "netlify-identity-widget";

import NavBar from "./components/NavBar";
import Home from "./pages/Home";
import Contact from "./pages/Contact";
import ContactFormSuccess from "./pages/ContactFormSuccess";
import ContactFormError from "./pages/ContactFormError";
import SamplePage from "./pages/SamplePage";
import {loginUser, logoutUser} from "./lib/identityActions";

import "./App.css";

function App() {
    const [user, setUser] = useState(null);

    const defaultMapView = {
        latitude: 33.1499819, longitude: -96.8340679, zoom: 11.45
    };

    const [mapLocations, setMapLocations] = useState(null);
    const [mapMode, setMapMode] = useState('dark');
    const [mapViewState, setMapViewState] = useState(defaultMapView)
    const [selectedLocation, setSelectedLocation] = useState(null);

    useEffect(() => {
        const userCookie = localStorage.getItem("currentOpenSaucedUser");

        if (userCookie) {
            setUser(JSON.parse(userCookie))
        } else {
            loginUser();
        }

        netlifyIdentity.on("login", () => {
            loginUser()
            setUser(JSON.parse(localStorage.getItem("currentOpenSaucedUser")));
        });

        netlifyIdentity.on("logout", () => {
            setUser(null)
            logoutUser()
        });
    }, [])

    return (
        <Router>
            <div className={"content"}>
                <NavBar user={user}/>
                <Routes>
                    <Route path={"/"} exact element={<Home mapLocations={mapLocations} setMapLocations={setMapLocations}
                                                           mapMode={mapMode} setMapMode={setMapMode}
                                                           mapViewState={mapViewState}
                                                           setMapViewState={setMapViewState}
                                                           selectedLocation={selectedLocation}
                                                           setSelectedLocation={setSelectedLocation} defaultMapView={defaultMapView}/>}/>
                    <Route path={"/contact"} element={<Contact userEmail={user ? user.email : ''}/>}/>
                    <Route path={"/contact-form-success"} element={<ContactFormSuccess />}/>
                    <Route path={"/contact-form-error"} element={<ContactFormError />}/>
                    <Route
                        path={"/about"}
                        element={<SamplePage title={"About"}>Explore. Design. Solve! is an organization committed to making the community a better place. Frisco Pollution Hotspots is a project by the Centennial HS Chapter of EDS that aims to make Frisco, TX a cleaner place. The app works by crowdsourcing data from users around the city and intuitively places markers around a map of Frisco to indicate places of high pollution. Users can then choose to volunteer to help clean up an area.</SamplePage>}
                    />
                    <Route
                        path={"/opportunities"}
                        element={
                        <SamplePage title={"opportunities"}>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                gap:"4px"
                            }}>
                                {"Please report any pollution hotspots here: "}
                                <a className={"link"} target="_blank" href={'https://docs.google.com/forms/d/e/1FAIpQLScy0oDV8ZR4-bjmzD0sQnP6F750WCfgDGH-szOb8JtYEcLtlA/viewform'} rel="noreferrer">Submission Form</a>
                            </div>
                        </SamplePage>}
                    />
                    <Route
                        path="*"
                        element={<SamplePage title={"404"}>404 Page Not Found</SamplePage>}
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
