import React, { useState, useEffect } from 'react';
import { 
  LayoutGrid, 
  MoreVertical, 
  ArrowDownCircle, 
  ArrowUpCircle, 
  Power, 
  ChevronDown,
  CheckCircle2
} from 'lucide-react';
import { motion, AnimatePresence } from 'motion/react';
import { clsx, type ClassValue } from 'clsx';
import { twMerge } from 'tailwind-merge';
import { VPN_CONFIG } from './config';

function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export default function App() {
  const [isConnected, setIsConnected] = useState(false);
  const [time, setTime] = useState(0);
  
  const [selectedServer, setSelectedServer] = useState(VPN_CONFIG.Servers[5]); // Dynamic Server 6
  const [selectedNetwork, setSelectedNetwork] = useState(VPN_CONFIG.HTTPNetworks[5]); // Lebara Free V2ray✅
  const [showServerModal, setShowServerModal] = useState(false);
  const [showNetworkModal, setShowNetworkModal] = useState(false);

  useEffect(() => {
    let interval: NodeJS.Timeout;
    if (isConnected) {
      interval = setInterval(() => {
        setTime(prev => prev + 1);
      }, 1000);
    } else {
      setTime(0);
    }
    return () => clearInterval(interval);
  }, [isConnected]);

  const formatTime = (seconds: number) => {
    const h = Math.floor(seconds / 3600).toString().padStart(2, '0');
    const m = Math.floor((seconds % 3600) / 60).toString().padStart(2, '0');
    const s = (seconds % 60).toString().padStart(2, '0');
    return `${h}:${m}:${s}`;
  };

  return (
    <div className="min-h-screen flex flex-col max-w-md mx-auto bg-white shadow-2xl overflow-hidden font-sans select-none relative">
      {/* Header */}
      <header className="px-4 pt-6 pb-2 flex items-center justify-between">
        <button className="p-2 active:scale-90 transition-transform">
          <LayoutGrid className="w-8 h-8 text-slate-600" strokeWidth={2.5} />
        </button>
        <div className="text-center">
          <h1 className="text-[28px] font-black tracking-tight leading-none flex items-center justify-center gap-1">
            <span className="text-blue-500">Cloud V2Ray</span>
            <span className="text-slate-800">-ViP</span>
          </h1>
          <p className="text-[12px] text-slate-400 font-bold tracking-[0.1em] mt-1">
            lightening and universe xo
          </p>
        </div>
        <button className="p-2 active:scale-90 transition-transform">
          <MoreVertical className="w-8 h-8 text-slate-600" strokeWidth={2.5} />
        </button>
      </header>

      {/* Graph Area / Stats Line */}
      <div className="relative h-24 px-4 mt-4">
        <div className="absolute right-10 top-0 flex flex-col items-end text-[10px] text-slate-300 font-mono leading-[1.2]">
          <span>0 bit</span>
          <span>0 bit</span>
          <span>0.0 bit</span>
          <div className="flex items-center gap-1">
            <span className="text-[10px] text-slate-400 font-bold">LiveData</span>
            <div className="w-2 h-2 rounded-full bg-red-400" />
            <span>0.0 bit</span>
          </div>
        </div>
        <div className="w-full h-[1px] bg-slate-100 mt-16" />
        <div className="flex justify-between px-2 mt-1 text-[11px] text-slate-400 font-bold">
          <span>Config: 6.97</span>
          <span>192.168.0.106</span>
          <span>Wifi connection</span>
        </div>
      </div>

      {/* Timer Section */}
      <div className="flex flex-col items-center pt-4 pb-4">
        <p className="text-[16px] text-slate-400 font-bold mb-1">Connection Time</p>
        <h2 className="text-[64px] font-mono font-medium text-blue-500 tracking-[-0.02em] leading-none">
          {formatTime(time)}
        </h2>
      </div>

      {/* Speed Metrics & V2Ray Label */}
      <div className="px-6 flex justify-between items-center mb-6">
        <div className="flex items-center gap-2">
          <ArrowDownCircle className="w-6 h-6 text-emerald-500" strokeWidth={2} />
          <div className="flex flex-col">
            <span className="text-[13px] font-bold text-slate-400">Download</span>
            <span className="text-[12px] font-bold text-slate-800 leading-none">0 B</span>
          </div>
        </div>
        <div className="text-[13px] text-slate-400 font-bold">V2Ray/Xray</div>
        <div className="flex items-center gap-2 text-right">
          <div className="flex flex-col">
            <span className="text-[13px] font-bold text-slate-400">Upload</span>
            <span className="text-[12px] font-bold text-slate-800 leading-none">0 B</span>
          </div>
          <ArrowUpCircle className="w-6 h-6 text-red-500" strokeWidth={2} />
        </div>
      </div>

      {/* Server Selection Cards */}
      <div className="px-5 space-y-3 mb-10">
        <button 
          onClick={() => setShowServerModal(true)}
          className="w-full bg-white border border-blue-200 rounded-[18px] p-4 flex items-center gap-4 active:scale-[0.98] transition-all"
        >
          <div className="w-12 h-12 rounded-full border border-blue-100 flex flex-col items-center justify-center relative overflow-hidden bg-white">
            <div className="flex gap-0.5">
               <div className="w-1 h-2 bg-blue-400 rounded-full" />
               <div className="w-1 h-3 bg-blue-400 rounded-full" />
               <div className="w-1 h-4 bg-blue-400 rounded-full" />
            </div>
            <span className="text-[10px] font-black text-slate-800 mt-0.5">5G</span>
          </div>
          <div className="flex-1 text-left">
            <h3 className="text-[17px] font-bold text-slate-700 leading-tight">{selectedServer.Name}</h3>
            <p className="text-[14px] text-blue-400 font-bold">Cloud V2Ray ViP</p>
          </div>
          <div className="text-[15px] text-slate-400 font-bold pr-2">~ ms</div>
        </button>

        <button 
          onClick={() => setShowNetworkModal(true)}
          className="w-full bg-white border border-blue-200 rounded-[18px] p-4 flex items-center gap-4 active:scale-[0.98] transition-all"
        >
          <div className="w-12 h-12 rounded-full bg-blue-400 flex items-center justify-center overflow-hidden">
             <div className="text-white font-black text-[9px] text-center leading-tight">
               Lebara
             </div>
          </div>
          <div className="flex-1 text-left">
            <div className="flex items-center gap-1">
              <h3 className="text-[17px] font-bold text-slate-700 leading-tight">{selectedNetwork.Name}</h3>
            </div>
            <p className="text-[14px] text-blue-400 font-bold">
              (High Speed💥 All Area)
            </p>
          </div>
          <div className="bg-blue-500 text-[10px] text-white px-2 py-1 rounded-full font-bold">
            V2ray/Xray
          </div>
        </button>
      </div>

      {/* Main Connection Button */}
      <div className="flex-1 flex flex-col items-center justify-center pb-16">
        <div className="relative cursor-pointer" onClick={() => setIsConnected(!isConnected)}>
          <div className={cn(
            "w-48 h-48 rounded-full border-[12px] flex items-center justify-center transition-all duration-500",
            isConnected ? "border-blue-500 bg-blue-500" : "border-blue-100 bg-white"
          )}>
            <Power className={cn(
              "w-20 h-20 transition-colors duration-500", 
              isConnected ? "text-white" : "text-blue-500"
            )} strokeWidth={3} />
          </div>
        </div>

        <div className="mt-10 text-center">
          <p className="text-[18px] font-bold text-slate-500">Account Expiration Details</p>
          <p className="text-[15px] font-bold text-blue-500 mt-1">
            Expiry: March 14, 2026 | 16 Days
          </p>
        </div>
      </div>

      {/* Bottom Navigation / Status */}
      <footer className="bg-slate-50 border-t border-slate-200 p-5 flex items-center justify-between">
        <div className="flex items-center gap-6">
          <ChevronDown className="w-8 h-8 text-slate-500" strokeWidth={2} />
          <span className="text-[16px] font-bold text-slate-600 uppercase tracking-wide">
            STATUS: {isConnected ? "Connected" : "Disconnected"}
          </span>
        </div>
        <MoreVertical className="w-8 h-8 text-slate-500" strokeWidth={2} />
      </footer>

      {/* Selection Modals */}
      <AnimatePresence>
        {showServerModal && (
          <SelectionModal 
            title="Select Server" 
            items={VPN_CONFIG.Servers} 
            selectedId={selectedServer.Name}
            onSelect={(server) => {
              setSelectedServer(server);
              setShowServerModal(false);
            }}
            onClose={() => setShowServerModal(false)}
          />
        )}
        {showNetworkModal && (
          <SelectionModal 
            title="Select Network" 
            items={VPN_CONFIG.HTTPNetworks} 
            selectedId={selectedNetwork.Name}
            onSelect={(network) => {
              setSelectedNetwork(network);
              setShowNetworkModal(false);
            }}
            onClose={() => setShowNetworkModal(false)}
          />
        )}
      </AnimatePresence>
    </div>
  );
}

function SelectionModal({ title, items, selectedId, onSelect, onClose }: { 
  title: string, 
  items: any[], 
  selectedId: string,
  onSelect: (item: any) => void, 
  onClose: () => void 
}) {
  const [search, setSearch] = useState("");
  const filteredItems = items.filter(item => 
    item.Name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <motion.div 
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      className="fixed inset-0 z-50 flex items-end justify-center bg-black/40 backdrop-blur-sm p-4"
      onClick={onClose}
    >
      <motion.div 
        initial={{ y: "100%" }}
        animate={{ y: 0 }}
        exit={{ y: "100%" }}
        transition={{ type: "spring", damping: 25, stiffness: 200 }}
        className="w-full max-w-md bg-white rounded-t-[32px] overflow-hidden flex flex-col max-h-[85vh]"
        onClick={e => e.stopPropagation()}
      >
        <div className="p-6 flex items-center justify-between border-b border-slate-50">
          <h2 className="text-xl font-black text-slate-800 tracking-tight">{title}</h2>
        </div>

        <div className="px-6 py-4">
          <input 
            type="text" 
            placeholder="Search..." 
            value={search}
            onChange={e => setSearch(e.target.value)}
            className="w-full bg-slate-50 border-none rounded-2xl py-3 px-4 text-slate-700 font-bold focus:ring-2 focus:ring-blue-500 transition-all"
          />
        </div>

        <div className="flex-1 overflow-y-auto px-4 pb-8 space-y-2">
          {filteredItems.map((item, idx) => (
            <button 
              key={idx}
              onClick={() => onSelect(item)}
              className={cn(
                "w-full flex items-center gap-4 p-4 rounded-2xl transition-all",
                selectedId === item.Name ? "bg-blue-50 border-2 border-blue-500/20" : "hover:bg-slate-50"
              )}
            >
              <div className="flex-1 text-left">
                <p className="text-[15px] font-bold text-slate-700 leading-tight">{item.Name}</p>
                <p className="text-[12px] text-slate-400 font-medium mt-0.5">{item.Info || "High Speed Server"}</p>
              </div>
              {selectedId === item.Name && (
                <CheckCircle2 className="w-5 h-5 text-blue-500" />
              )}
            </button>
          ))}
        </div>
      </motion.div>
    </motion.div>
  );
}
