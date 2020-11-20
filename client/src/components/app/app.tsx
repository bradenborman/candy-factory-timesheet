import React, { useState, useEffect } from "react";

require("./app.scss");

export interface IAppProps {}

export const App: React.FC<IAppProps> = (props: IAppProps) => {
 
//   const [playSpeed, setPlaySpeed] = useState<number>(1500);

  useEffect(() => {
  }, []);

//   const fetcAttemptData = async () => {
//     const requestBody: SimulationRequest = {
//       startingLocations: [StartingLocation.HALF_COURT, StartingLocation.LAYUP]
//     };
//     try {
//       const res: any = await axios.post(`/api/simulate`, requestBody);
//       if (res.data) {
//         const response: SimulationResponse = res.data;
//         setFullSimulationResponse(response);
//       }
//     } catch (err) {
//       console.error(err);
//     }
//   };

  
  return (
      <div className="app-wrapper">
          Test
    </div>
  );
};
