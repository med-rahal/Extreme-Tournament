<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReclamationController extends AbstractController
{
    /**
     * @Route("/reclamation", name="app_reclamation")
     */
    public function indexfront(): Response
    {
        return $this->render('reclamation/index.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    }
    /**
     * @Route("/reclam", name="app_reclamationback")
     */
    public function indexback(): Response
    {
        return $this->render('reclamation/reclamback.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    }
}
